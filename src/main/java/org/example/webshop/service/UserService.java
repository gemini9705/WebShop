package org.example.webshop.service;

import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.User;
import org.example.webshop.controller.UserDTO;

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
     * Retrieves a user from the database based on their username and converts to DTO.
     *
     * @param username the username of the user to retrieve
     * @return the UserDTO object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public UserDTO getUserByUsername(String username) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        return (user != null) ? convertToDTO(user) : null;
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

    /**
     * Verifies if the user's password matches and returns a DTO.
     *
     * @param username the username to check
     * @param password the password to verify
     * @return the UserDTO object if the password is correct, or null if not
     * @throws SQLException if a database access error occurs
     */
    public UserDTO verifyUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return convertToDTO(user);
        }
        return null;
    }

    /**
     * Converts a User object to a UserDTO.
     *
     * @param user the User to convert
     * @return the corresponding UserDTO
     */
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
