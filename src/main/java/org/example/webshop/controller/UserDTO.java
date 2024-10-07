package org.example.webshop.controller;

/**
 * Data Transfer Object (DTO) for transferring user data between layers.
 * This class is used to encapsulate user information, specifically the user ID and username,
 * in order to transfer user-related data without exposing the full user model.
 */
public class UserDTO {
    private int id;
    private String username;

    /**
     * Constructs a UserDTO with the specified user details.
     *
     * @param id       the unique identifier for the user
     * @param username the username of the user
     */
    public UserDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the user ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
