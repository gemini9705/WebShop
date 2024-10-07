package org.example.webshop.controller;

import org.example.webshop.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/WEB-INF/products.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving products", e);
        }
    }
}
