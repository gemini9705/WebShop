package org.example.webshop.service;

import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.User;

import java.sql.SQLException;

/**
 * Service class for managing users in the webshop.
 * Provides methods to retrieve and add users.
 */
public class UserService {
    private UserDAO userDAO;

    /**
     * Constructs a UserService and initializes the UserDAO.
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Retrieves a user from the database based on their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    /**
     * Adds a new user to the database.
     *
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @throws SQLException if a database access error occurs
     */
    public void addUser(String username, String password) throws SQLException {
        userDAO.addUser(username, password);
    }
}
