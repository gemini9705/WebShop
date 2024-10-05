package org.example.webshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.webshop.model.Product;
import org.example.webshop.model.User;
import org.example.webshop.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if user is logged in
        if (user != null) {
            try {
                // Retrieve productId and quantity from form
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Validate that quantity is positive
                if (quantity <= 0) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity must be greater than 0.");
                    return;
                }

                ProductService productService = new ProductService();
                Product product = productService.getProductById(productId);

                // Retrieve or create cart in session
                List<Product> cart = (List<Product>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                    session.setAttribute("cart", cart);
                }

                // Check if product is already in cart
                boolean productExists = false;
                for (Product cartProduct : cart) {
                    if (cartProduct.getId() == productId) {
                        // Product already exists in cart, increase quantity
                        cartProduct.setStock(cartProduct.getStock() + quantity);
                        productExists = true;
                        break;
                    }
                }

                // If product is not in cart, add as a new item
                if (!productExists) {
                    product.setStock(quantity);
                    cart.add(product);
                }

                // Redirect back to cart page
                response.sendRedirect("view-cart");

            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding product to cart.");
            }
        } else {
            // If user is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
