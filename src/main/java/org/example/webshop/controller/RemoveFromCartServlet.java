package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import org.example.webshop.model.Product;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    /**
     * Processes the HTTP POST request to remove a product from the shopping cart.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs during the processing
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve the product ID from the form
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Retrieve the shopping cart from the session
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart != null) {
            // Remove the product from the cart based on the product ID
            cart.removeIf(product -> product.getId() == productId);
        }

        // Update the shopping cart in the session
        session.setAttribute("cart", cart);

        // Redirect the user back to the cart view
        response.sendRedirect("view-cart");
    }
}
