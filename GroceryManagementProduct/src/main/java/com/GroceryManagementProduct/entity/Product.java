//Entity
package com.GroceryManagementProduct.entity;

//Import the required packages
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table
public class Product {
	// Unique identifier for the product
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id; 
   // Name of the product
    @Column
    private String product_name; 
    // Category of the product
    @Column
    private String category; 
   // Price of the product
    @Column
    private int price; 
    // Availability of the product
    @Column
    private String availability; 

    public Product() {	
    }

    // Constructor to initialize Product with name, category, price, and availability
    public Product(String product_name, String category, int price, String availability) {
        super();
        this.product_name = product_name;
        this.category = category;
        this.price = price;
        this.availability = availability;
    }

    // Getters and setters for all fields
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Product [product_id=" + product_id + ", product_name=" + product_name + ", category=" + category
                + ", price=" + price + ", availability=" + availability + "]";
    }
}
