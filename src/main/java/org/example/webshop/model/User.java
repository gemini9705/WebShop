package org.example.webshop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Product> cart;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<>();  // Initialize cart as a new ArrayList
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Product> getCart() {
        return cart;
    }

    // Add product to cart
    public void addToCart(Product product) {
        cart.add(product);
    }

    // Optional: Get the total number of items in the cart
    public int getCartSize() {
        return cart.size();
    }
}
