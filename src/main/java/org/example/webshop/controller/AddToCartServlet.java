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
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                CartService cartService = new CartService();

                // Validera kvantiteten
                if (!cartService.isValidQuantity(quantity)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity must be greater than 0.");
                    return;
                }

                // Hämta varukorgen från sessionen
                List<Product> cart = cartService.getCartFromSession(session);

                // Lägg till produkten i varukorgen
                cartService.addProductToCart(cart, productId, quantity);

                // Uppdatera varukorgen i sessionen
                cartService.updateCartInSession(session, cart);

                // Omdirigera till varukorgssidan
                response.sendRedirect("view-cart");

            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding product to cart.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
