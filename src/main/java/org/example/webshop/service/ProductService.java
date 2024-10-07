package org.example.webshop.service;

import org.example.webshop.dao.ProductDAO;
import org.example.webshop.model.Product;
import org.example.webshop.controller.ProductDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Retrieves all products from the database and converts them to DTOs.
     *
     * @return a list of all ProductDTOs
     * @throws SQLException if a database access error occurs
     */
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<Product> products = productDAO.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = convertToDTO(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }


    /**
     * Converts a Product to a ProductDTO.
     *
     * @param product the Product to convert
     * @return the corresponding ProductDTO
     */
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock());
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
     * Retrieves a product by its ID and converts it to a DTO.
     *
     * @param id the ID of the product
     * @return the corresponding ProductDTO if found, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public ProductDTO getProductById(int id) throws SQLException {
        Product product = productDAO.getProductById(id);
        return (product != null) ? convertToDTO(product) : null;
    }
}
