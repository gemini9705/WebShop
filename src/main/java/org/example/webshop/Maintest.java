package org.example.webshop;

import org.example.webshop.dao.ProductDAO;
import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.Product;
import org.example.webshop.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Maintest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();

        try {
            // Skapa en användare (vi kan lägga till funktioner att hämta från databasen senare)
            User user = new User(1, "testUser", "testPassword");

            // Hämta alla produkter från databasen
            List<Product> products = productDAO.getAllProducts();

            // Visa alla produkter
            System.out.println("Available products:");
            for (Product product : products) {
                System.out.println(product.getId() + ": " + product.getName() + " - " + product.getPrice() + " SEK");
            }

            // Lägg till produkter i användarens varukorg
            while (true) {
                System.out.println("Enter product ID to add to cart (or 0 to finish):");
                int productId = scanner.nextInt();
                if (productId == 0) {
                    break;  // Avsluta om användaren skriver 0
                }

                // Hitta produkten med angivet ID
                Product selectedProduct = null;
                for (Product product : products) {
                    if (product.getId() == productId) {
                        selectedProduct = product;
                        break;
                    }
                }

                if (selectedProduct != null) {
                    user.addToCart(selectedProduct);
                    System.out.println(selectedProduct.getName() + " added to cart.");
                } else {
                    System.out.println("Product not found.");
                }
            }

            // Visa innehållet i varukorgen
            System.out.println("Your cart contains:");
            for (Product product : user.getCart()) {
                System.out.println(product.getName() + " - " + product.getPrice() + " SEK");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
