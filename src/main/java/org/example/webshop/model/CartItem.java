package org.example.webshop.model;

/**
 * Represents an item in the shopping cart, containing a product and its quantity.
 */
public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Constructs a CartItem with the specified product and quantity.
     *
     * @param product  the product associated with this cart item
     * @param quantity the quantity of the product
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Returns the product associated with this cart item.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this cart item.
     *
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the quantity of the product in this cart item.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in this cart item.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
