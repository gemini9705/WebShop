package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;
import org.example.webshop.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle displaying the list of products.
 * This servlet processes HTTP GET requests to fetch a list of products from the ProductService
 * and forwards the data to a JSP page for rendering.
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    /**
     * Processes the HTTP GET request to display the list of products.
     * It retrieves the products from the ProductService, sets the list of products as a request attribute,
     * and forwards the request to the JSP page for rendering. Any session messages are also passed to the JSP.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();

        // Retrieve the list of products from ProductService
        List<Product> products = null;
        try {
            products = productService.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set the products as a request attribute to be forwarded to the JSP page
        request.setAttribute("products", products);

        // Retrieve any session message and pass it to the JSP
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message"); // Remove the message after displaying
        }

        // Forward the request to the products.jsp page
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
