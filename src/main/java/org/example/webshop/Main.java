package org.example.webshop;

import org.example.webshop.dao.ProductDAO;
import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.Product;
import org.example.webshop.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Skapa DAO-objekt för att hantera databasinteraktioner
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();

        // Testa att lägga till en användare
        try {
            System.out.println("Adding a new user...");
            userDAO.addUser("test22", "hsdjhjwh");
            System.out.println("User added!");

            // Testa att hämta användare från databasen
            System.out.println("Retrieving user by username...");
            User retrievedUser = userDAO.getUserByUsername("test22");
            if (retrievedUser != null) {
                System.out.println("User retrieved: " + retrievedUser.getUsername());
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Testa att lägga till en produkt
        try {
            System.out.println("Adding a new product...");
            productDAO.addProduct("Car", "BMW ", 15000.0, 4);
            System.out.println("Product added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Testa att hämta alla produkter
        try {
            System.out.println("Fetching all products...");
            List<Product> productList = productDAO.getAllProducts();

            // Om produkttabellen inte är tom, lista alla produkter
            if (!productList.isEmpty()) {
                for (Product product : productList) {
                    System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice() + ", Stock: " + product.getStock());
                }
            } else {
                System.out.println("No products found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
