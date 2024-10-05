package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/view-cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hämta varukorgen från sessionen
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        // Bygg HTML-tabellen för kundvagnen
        StringBuilder cartTableHtml = new StringBuilder();
        if (cart != null && !cart.isEmpty()) {
            for (Product product : cart) {
                cartTableHtml.append("<tr>")
                        .append("<td>").append(product.getId()).append("</td>")
                        .append("<td>").append(product.getName()).append("</td>")
                        .append("<td>").append(product.getDescription()).append("</td>")
                        .append("<td>").append(product.getPrice()).append(" kr</td>")
                        .append("<td>").append(product.getStock()).append("</td>")
                        .append("<td>")
                        .append("<form action='remove-from-cart' method='post'>")
                        .append("<input type='hidden' name='productId' value='").append(product.getId()).append("'>")
                        .append("<input type='submit' value='Ta bort'>")
                        .append("</form>")
                        .append("</td>")
                        .append("</tr>");
            }
        } else {
            cartTableHtml.append("<tr><td colspan='6'>Din kundvagn är tom.</td></tr>");
        }

        // Sätt HTML-strängen som en attribut för JSP
        request.setAttribute("cartTableHtml", cartTableHtml.toString());

        // Vidarebefordra till JSP-sidan
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
