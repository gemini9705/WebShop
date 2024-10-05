package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import org.example.webshop.model.Product;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Hämta produktens ID från formuläret
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Hämta varukorgen från sessionen
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart != null) {
            // Ta bort produkten från varukorgen baserat på produkt-ID
            cart.removeIf(product -> product.getId() == productId);
        }

        // Uppdatera sessionens varukorg
        session.setAttribute("cart", cart);

        // Skicka tillbaka användaren till varukorgen
        response.sendRedirect("view-cart");
    }
}
