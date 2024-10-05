package org.example.webshop.dao;

import org.example.webshop.db.DatabaseConnection;
import org.example.webshop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Hämta användare baserat på användarnamn
    public User getUserByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Users WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        // Debug: Kontrollera resultatet av frågan
        System.out.println("Kör query: " + query);
        System.out.println("För användarnamn: " + username);

        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        }
        return null;
    }


    // Lägg till en användare i databasen
    public void addUser(String username, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
    }
}
