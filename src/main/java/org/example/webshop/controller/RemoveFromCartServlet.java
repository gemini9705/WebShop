package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import org.example.webshop.model.Product;
import org.example.webshop.service.CartService;

/**
 * Servlet to handle removing products from the shopping cart.
 * This servlet processes POST requests to remove a specific product from the user's shopping cart.
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request to remove a product from the shopping cart.
     * Retrieves the product ID from the request, fetches the cart from the session,
     * removes the specified product from the cart, updates the session, and redirects the user back to the cart view page.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if an error occurs during the request processing
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve the product ID from the request
        int productId = Integer.parseInt(request.getParameter("productId"));

        CartService cartService = new CartService();

        // Retrieve the cart from the session
        List<Product> cart = cartService.getCartFromSession(session);

        if (cart != null) {
            // Use CartService to remove the product from the cart
            cartService.removeProductFromCart(cart, productId);

            // Update the cart in the session
            cartService.updateCartInSession(session, cart);
        }

        // Redirect the user back to the cart view page
        response.sendRedirect("view-cart");
    }
}
