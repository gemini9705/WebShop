package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;

import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle displaying the shopping cart.
 */
@WebServlet("/view-cart")
public class CartServlet extends HttpServlet {

    /**
     * Processes the HTTP GET request to display the shopping cart.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs during the processing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        // Skicka varukorgen som en request-attribut till JSP-filen
        request.setAttribute("cart", cart);

        // Forward the request to the cart.jsp page
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
