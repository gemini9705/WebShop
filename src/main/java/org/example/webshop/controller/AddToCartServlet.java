package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;
import org.example.webshop.model.User;
import org.example.webshop.service.CartService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to handle adding products to the shopping cart.
 * This servlet processes the addition of a product to a user's shopping cart by validating the quantity
 * and updating the cart stored in the session. If the user is not logged in, they are redirected to the login page.
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request to add a product to the shopping cart.
     * It validates the product ID and quantity from the request, checks if the user is logged in, and then adds the product
     * to the shopping cart stored in the session.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error occurs while the servlet is handling the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if the user is logged in
        if (user != null) {
            try {
                // Parse the product ID and quantity from the request
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                CartService cartService = new CartService();

                // Validate the quantity
                if (!cartService.isValidQuantity(quantity)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity must be greater than 0.");
                    return;
                }

                // Retrieve the cart from the session
                List<Product> cart = cartService.getCartFromSession(session);

                // Add the product to the cart
                cartService.addProductToCart(cart, productId, quantity);

                // Update the cart in the session
                cartService.updateCartInSession(session, cart);

                // Redirect to the view cart page
                response.sendRedirect("view-cart");

            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding product to cart.");
            }
        } else {
            // Redirect to the login page if the user is not logged in
            response.sendRedirect("login.jsp");
        }
    }
}
