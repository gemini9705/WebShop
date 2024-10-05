package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;
import org.example.webshop.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ProductServlet doGet() körs");
        ProductService productService = new ProductService();

        // Hämta lista över produkter från servicelagret
        List<Product> products = null;
        try {
            products = productService.getAllProducts();
            System.out.println("Antal produkter hämtade: " + products.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Hantera produkttabell som HTML-sträng
        StringBuilder productTableHtml = new StringBuilder();
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                productTableHtml.append("<tr>")
                        .append("<td>").append(product.getId()).append("</td>")
                        .append("<td>").append(product.getName()).append("</td>")
                        .append("<td>").append(product.getDescription()).append("</td>")
                        .append("<td>").append(product.getPrice()).append(" kr</td>")
                        .append("<td>").append(product.getStock()).append("</td>")
                        .append("<td>")
                        .append("<form action='add-to-cart' method='post'>")
                        .append("<input type='hidden' name='productId' value='").append(product.getId()).append("'>")
                        .append("<label for='quantity_").append(product.getId()).append("'>Antal:</label>")
                        .append("<input type='number' id='quantity_").append(product.getId()).append("' name='quantity' value='1' min='1' required>")
                        .append("<input type='submit' value='Lägg till'>")
                        .append("</form>")
                        .append("</td>")
                        .append("</tr>");
            }
        } else {
            productTableHtml.append("<tr><td colspan='6'>Inga produkter tillgängliga.</td></tr>");
        }

        // Hantera sessionens meddelanden
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message");
        }

        // Lägg till produkttabell som HTML-sträng
        request.setAttribute("productTableHtml", productTableHtml.toString());

        // Skicka vidare till JSP-sidan
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
