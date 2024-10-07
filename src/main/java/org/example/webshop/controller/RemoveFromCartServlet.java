package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import org.example.webshop.model.Product;
import org.example.webshop.service.CartService;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int productId = Integer.parseInt(request.getParameter("productId")); // Hämta produktens ID från förfrågan

        CartService cartService = new CartService();

        // Hämta varukorgen från sessionen
        List<Product> cart = cartService.getCartFromSession(session);

        if (cart != null) {
            // Använd CartService för att hantera borttagningen
            cartService.removeProductFromCart(cart, productId);

            // Uppdatera varukorgen i sessionen
            cartService.updateCartInSession(session, cart);
        }

        // Omdirigera användaren tillbaka till varukorgsidan
        response.sendRedirect("view-cart");
    }
}
