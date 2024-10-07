package org.example.webshop.service;

import org.example.webshop.model.Product;
import org.example.webshop.dao.ProductDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

public class CartService {

    private final ProductDAO productDAO;

    public CartService() {
        this.productDAO = new ProductDAO();
    }

    // Hantering av att lägga till produkter till varukorgen
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

    // Hantera borttagning av produkter från varukorgen
    public void removeProductFromCart(List<Product> cart, int productId) {
        cart.removeIf(product -> product.getId() == productId);
    }

    // Validera kvantitet
    public boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    // Hämta varukorgen från sessionen
    public List<Product> getCartFromSession(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // Uppdatera varukorgen i sessionen
    public void updateCartInSession(HttpSession session, List<Product> cart) {
        session.setAttribute("cart", cart);
    }
}
