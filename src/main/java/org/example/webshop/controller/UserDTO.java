package org.example.webshop.controller;

/**
 * Data Transfer Object for transferring user data.
 */
public class UserDTO {
    private int id;
    private String username;

    // Constructor
    public UserDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
