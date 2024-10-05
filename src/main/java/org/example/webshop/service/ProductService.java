package org.example.webshop.service;

import org.example.webshop.dao.ProductDAO;
import org.example.webshop.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = productDAO.getAllProducts();
        return products;
    }

    public void addProduct(String name, String description, double price, int stock) throws SQLException {
        productDAO.addProduct(name, description, price, stock);
    }

    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }
}
