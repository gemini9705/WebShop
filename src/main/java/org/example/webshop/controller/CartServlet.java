package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;

import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle displaying the shopping cart.
 * This servlet processes the HTTP GET request to retrieve and display the user's shopping cart.
 * The cart is stored in the session, and its contents are passed as a request attribute to the JSP page for rendering.
 */
@WebServlet("/view-cart")
public class CartServlet extends HttpServlet {

    /**
     * Handles the HTTP GET request to display the shopping cart.
     * It retrieves the shopping cart from the session, sets it as a request attribute,
     * and forwards the request to the corresponding JSP page for display.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve the shopping cart from the session
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        // Set the cart as a request attribute to be forwarded to the JSP page
        request.setAttribute("cart", cart);

        // Forward the request to the cart.jsp page for display
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
