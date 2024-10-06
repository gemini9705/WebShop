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

/**
 * Servlet to handle adding products to the shopping cart.
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    /**
     * Processes the HTTP POST request to add a product to the cart.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs during the processing
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Check if the user is logged in
        if (user != null) {
            try {
                // Retrieve product ID and quantity from the request
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // Validate quantity
                if (quantity <= 0) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity must be greater than 0.");
                    return;
                }

                ProductService productService = new ProductService();
                Product product = productService.getProductById(productId);

                // Retrieve the shopping cart from the session
                List<Product> cart = (List<Product>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                    session.setAttribute("cart", cart);
                }

                // Check if the product already exists in the cart
                boolean productExists = false;
                for (Product cartProduct : cart) {
                    if (cartProduct.getId() == productId) {
                        cartProduct.setStock(cartProduct.getStock() + quantity);
                        productExists = true;
                        break;
                    }
                }

                // If the product does not exist, add it to the cart
                if (!productExists) {
                    product.setStock(quantity);
                    cart.add(product);
                }

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
