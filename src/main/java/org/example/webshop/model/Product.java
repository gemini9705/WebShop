package org.example.webshop.model;

/**
 * Represents a product in the webshop, including its details and stock information.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;

    /**
     * Default constructor for the Product class.
     */
    public Product() {
    }

    /**
     * Constructs a Product with the specified parameters matching the database schema.
     *
     * @param id          the unique identifier for the product
     * @param name        the name of the product
     * @param description a description of the product
     * @param price       the price of the product
     */
    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and setters

    /**
     * Returns the unique identifier of the product.
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
     * Returns the name of the product.
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
     * Returns the description of the product.
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
     * Returns the price of the product.
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
     * Returns the stock quantity of the product.
     *
     * @return the product stock quantity
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stock the product stock quantity to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
