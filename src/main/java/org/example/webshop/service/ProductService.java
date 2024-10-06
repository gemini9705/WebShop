package org.example.webshop.service;

import org.example.webshop.dao.ProductDAO;
import org.example.webshop.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for managing products in the webshop.
 * Provides methods to retrieve, add, and get products by ID.
 */
public class ProductService {
    private ProductDAO productDAO;

    /**
     * Constructs a ProductService and initializes the ProductDAO.
     */
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     * @throws SQLException if a database access error occurs
     */
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = productDAO.getAllProducts();
        return products;
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
        productDAO.addProduct(name, description, price, stock);
    }

    /**
     * Retrieves a product from the database based on its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the Product object if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }
}
