package org.example.webshop.db;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Anslutningen till databasen är etablerad!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
