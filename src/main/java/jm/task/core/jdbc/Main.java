package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

       // UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.saveUser("Valera", "Petrov", (byte) 30);
       // userDaoJDBC.removeUserById(2);
        UserServiceImpl userService = new UserServiceImpl();
        userService.getAllUsers();

    }
}
