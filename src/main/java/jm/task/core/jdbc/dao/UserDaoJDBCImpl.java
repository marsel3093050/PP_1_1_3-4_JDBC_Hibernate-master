package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE user (Id INT PRIMARY KEY AUTO_INCREMENT, firstName VARCHAR(45), lastName VARCHAR(45), age INT)";
        try (Connection connection = Util.getConn()) {
            connection.createStatement().executeUpdate(sqlCommand);
            connection.commit();
            System.out.println("Таблица была создана!");
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS user";
        try (Connection connection = Util.getConn()) {
            connection.createStatement().executeUpdate(sqlCommand);
            connection.commit();
            System.out.println("Таблица была удалена!");
            connection.rollback();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConn()) {
            String sql = "INSERT INTO user (firstName, lastName, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rows = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных!");
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении!");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConn()) {
            String sql = "DELETE FROM user WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с id – " + id + " удалён из базы данных!");
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении!");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sqlCommand = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConn()) {
            ResultSet resultSet = connection.createStatement().executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                users.add(user);
            }
            connection.commit();
            for (int j = 0; j < users.size(); j++) {
                System.out.println(users.get(j).toString());
            }
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе пользователей!");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {


        String sqlCommand = "DELETE FROM user";
        try (Connection connection = Util.getConn()) {
            connection.createStatement().executeUpdate(sqlCommand);
            connection.commit();
            System.out.println("Данные из таблицы удалены!");
            connection.rollback();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении данных!");
            e.printStackTrace();
        }
    }
}

