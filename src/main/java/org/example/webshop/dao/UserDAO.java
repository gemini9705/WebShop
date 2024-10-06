package org.example.webshop.dao;

import org.example.webshop.db.DatabaseConnection;
import org.example.webshop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Retrieves a user from the database based on their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public User getUserByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Users WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        // Debug: Check the result of the query
        System.out.println("Kör query: " + query);
        System.out.println("För användarnamn: " + username);

        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        }
        return null;
    }

    /**
     * Adds a new user to the database.
     *
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @throws SQLException if a database access error occurs
     */
    public void addUser(String username, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
    }
}
