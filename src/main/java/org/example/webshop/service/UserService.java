package org.example.webshop.service;

import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    public void addUser(String username, String password) throws SQLException {
        userDAO.addUser(username, password);
    }
}
