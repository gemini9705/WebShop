package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;
import org.example.webshop.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    /**
     * Processes the HTTP GET request to display the list of products.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs during the processing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();

        // Hämta produkterna från ProductService
        List<Product> products = null;
        try {
            products = productService.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Skicka produkterna som en request-attribut till JSP-filen
        request.setAttribute("products", products);

        // Skicka eventuella meddelanden från sessionen till JSP
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message");
        }

        // Vidarebefordra till products.jsp
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
