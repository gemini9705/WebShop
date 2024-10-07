package org.example.webshop.controller;

import org.example.webshop.service.ProductService;
import org.example.webshop.controller.ProductDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle product listing requests.
 * This servlet retrieves all available products from the ProductService and forwards them
 * to a JSP page for display.
 */
//@WebServlet("/products") // Uncomment this annotation if you want to map this servlet to the "/products" URL
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    /**
     * Initializes the ProductServlet by creating a new instance of ProductService.
     * This method is called once when the servlet is created.
     *
     * @throws ServletException if an error occurs during servlet initialization
     */
    @Override
    public void init() throws ServletException {
        // Initialize ProductService when the servlet is created
        productService = new ProductService();
    }

    /**
     * Handles the HTTP GET request to retrieve the list of products.
     * It calls the ProductService to retrieve all products, sets the list as a request attribute,
     * and forwards the request to the JSP page "products.jsp" for rendering.
     *
     * @param req  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if an error occurs while processing the request
     * @throws IOException      if an input or output error occurs while the servlet is handling the request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve all products from ProductService
            List<ProductDTO> products = productService.getAllProducts();

            // Set products as a request attribute
            req.setAttribute("products", products);

            // Forward the request to the products.jsp page for display
            req.getRequestDispatcher("products.jsp").forward(req, resp);
        } catch (SQLException e) {
            // Handle any SQL errors and forward an error message to the user
            throw new ServletException("Error retrieving products", e);
        }
    }
}
