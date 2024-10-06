package org.example.webshop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the webshop, including their credentials and shopping cart.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private List<Product> cart;

    /**
     * Constructs a User with the specified ID, username, and password.
     *
     * @param id       the unique identifier for the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<>();  // Initialize cart as a new ArrayList
    }

    // Getters

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the shopping cart of the user.
     *
     * @return a list of products in the cart
     */
    public List<Product> getCart() {
        return cart;
    }

    /**
     * Adds a product to the user's shopping cart.
     *
     * @param product the product to add to the cart
     */
    public void addToCart(Product product) {
        cart.add(product);
    }

    /**
     * Returns the total number of items in the user's shopping cart.
     *
     * @return the number of items in the cart
     */
    public int getCartSize() {
        return cart.size();
    }
}
