package org.example.webshop.service;

import org.example.webshop.model.Product;
import org.example.webshop.dao.ProductDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

/**
 * Service class to handle operations related to the shopping cart.
 * This service provides methods for adding products to the cart, removing products, validating quantities,
 * and managing the cart stored in the session.
 */
public class CartService {

    private final ProductDAO productDAO;

    /**
     * Constructs a CartService and initializes the ProductDAO.
     */
    public CartService() {
        this.productDAO = new ProductDAO();
    }

    /**
     * Adds a product to the shopping cart. If the product is already in the cart, it updates the quantity.
     * If not, it adds the product to the cart.
     *
     * @param cart      the current shopping cart (list of products)
     * @param productId the ID of the product to add
     * @param quantity  the quantity of the product to add
     * @throws SQLException if a database access error occurs
     */
    public void addProductToCart(List<Product> cart, int productId, int quantity) throws SQLException {
        Product product = productDAO.getProductById(productId);

        boolean productExists = false;
        for (Product cartProduct : cart) {
            if (cartProduct.getId() == productId) {
                cartProduct.setStock(cartProduct.getStock() + quantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            product.setStock(quantity);
            cart.add(product);
        }
    }

    /**
     * Removes a product from the shopping cart based on its ID.
     *
     * @param cart      the current shopping cart (list of products)
     * @param productId the ID of the product to remove
     */
    public void removeProductFromCart(List<Product> cart, int productId) {
        cart.removeIf(product -> product.getId() == productId);
    }

    /**
     * Validates the quantity to ensure it is greater than zero.
     *
     * @param quantity the quantity to validate
     * @return true if the quantity is valid, otherwise false
     */
    public boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    /**
     * Retrieves the shopping cart from the session. If the cart does not exist, it creates a new one.
     *
     * @param session the current HTTP session
     * @return the shopping cart (list of products)
     */
    public List<Product> getCartFromSession(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    /**
     * Updates the shopping cart stored in the session.
     *
     * @param session the current HTTP session
     * @param cart    the updated shopping cart (list of products)
     */
    public void updateCartInSession(HttpSession session, List<Product> cart) {
        session.setAttribute("cart", cart);
    }
}
