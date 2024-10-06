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

        // Build the HTML table for the cart
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
            // Display a message if the cart is empty
            cartTableHtml.append("<tr><td colspan='6'>Din kundvagn är tom.</td></tr>");
        }

        // Set the cart table HTML as a request attribute
        request.setAttribute("cartTableHtml", cartTableHtml.toString());

        // Forward the request to the cart.jsp page
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
