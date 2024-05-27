package com.GroceryManagementSales.entity;

//import the required package
import com.GroceryManagementProduct.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing the details of a sales transaction.
 */
@Entity
@Table(name = "sales_details")
public class SalesDetails {

    // Primary key for SalesDetails entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_details_id")
    private int salesDetailsId;

    // Many-to-one relationship with Sales entity
    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sales sales;

    // Many-to-one relationship with Product entity
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Unit price of the product at the time of sale
    @Column(name = "unit_price")
    private double unitPrice;

    // Quantity of the product sold
    @Column(name = "quantity")
    private int quantity;

    // Total cost for the quantity of the product sold
    @Column(name = "cost")
    private double cost;

    /**
     * Default constructor.
     */
    public SalesDetails() {
    }

    /**
     * Parameterized constructor to initialize SalesDetails with sales, product, and quantity.
     * 
     * @param sales The sales transaction.
     * @param product The product being sold.
     * @param quantity The quantity of the product sold.
     */
    public SalesDetails(Sales sales, Product product, int quantity) {
        this.sales = sales;
        this.product = product;
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
        calculateCost();
    }

    // Getter for salesDetailsId
    public int getSalesDetailsId() {
        return salesDetailsId;
    }

    // Setter for salesDetailsId
    public void setSalesDetailsId(int salesDetailsId) {
        this.salesDetailsId = salesDetailsId;
    }

    // Getter for sales
    public Sales getSales() {
        return sales;
    }

    // Setter for sales
    public void setSales(Sales sales) {
        this.sales = sales;
    }

    // Getter for product
    public Product getProduct() {
        return product;
    }

    // Setter for product
    public void setProduct(Product product) {
        this.product = product;
        this.unitPrice = product.getPrice();
        calculateCost();
    }

    // Getter for unitPrice
    public double getUnitPrice() {
        return unitPrice;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateCost();
    }

    // Getter for cost
    public double getCost() {
        return cost;
    }

    // Setter for cost
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Calculate cost based on unit price and quantity.
     */
    private void calculateCost() {
        this.cost = this.unitPrice * this.quantity;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "SalesDetails{" +
                "salesDetailsId=" + salesDetailsId +
                ", sales=" + sales +
                ", product=" + product +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
