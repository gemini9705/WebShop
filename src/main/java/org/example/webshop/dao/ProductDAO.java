package org.example.webshop.dao;

import org.example.webshop.db.DatabaseConnection;
import org.example.webshop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Products";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price")
            );
            product.setStock(rs.getInt("stock"));
            productList.add(product);
        }

        ps.close();
        rs.close();
        connection.close();

        return productList;
    }

    /**
     * Adds a new product to the database.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param stock       the stock quantity of the product
     * @throws SQLException if a database access error occurs
     */
    public void addProduct(String name, String description, double price, int stock) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Products (name, description, price, stock) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setDouble(3, price);
        ps.setInt(4, stock);
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    /**
     * Retrieves a product from the database based on its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product object, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Product getProductById(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Products WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Product product = null;
        if (rs.next()) {
            product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price")
            );
            product.setStock(rs.getInt("stock"));
        }

        ps.close();
        rs.close();
        connection.close();

        return product;
    }
}
