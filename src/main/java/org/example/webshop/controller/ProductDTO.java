package org.example.webshop.controller;

/**
 * Data Transfer Object (DTO) for transferring product data between layers.
 * This class is used to encapsulate product details such as ID, name, description, price, and stock
 * without exposing the full product model. It acts as a simplified data carrier between the service and view layers.
 */
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;

    /**
     * Constructs a ProductDTO with the specified product details.
     *
     * @param id          the unique identifier for the product
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param stock       the number of items available in stock
     */
    public ProductDTO(int id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Gets the unique identifier of the product.
     *
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param id the product ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     *
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the product description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the product price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the available stock quantity of the product.
     *
     * @return the available stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the available stock quantity of the product.
     *
     * @param stock the stock quantity to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
