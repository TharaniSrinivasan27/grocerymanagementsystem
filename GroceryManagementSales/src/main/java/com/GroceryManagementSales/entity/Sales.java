package com.GroceryManagementSales.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.GroceryManagementCustomer.Entity.CustomerDetails;

@Entity
@Table(name = "sales")
public class Sales {
    
    // Primary key for the Sales entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private int salesId;

    // Column to store the total amount of the sale
    @Column(name = "total_amount")
    private double totalAmount;

    // Column to store the date of the sale
    @Column(name = "date_of_sale")
    private Timestamp dateOfSale;

    // Column to store the status of the sale
    @Column(name = "sales_status")
    private String salesStatus;

    // Many-to-one relationship with CustomerDetails entity
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerDetails customer;

    // Default constructor
    public Sales() {
    }

    // Parameterized constructor
    public Sales(double totalAmount, Timestamp dateOfSale, String salesStatus, CustomerDetails customer) {
        this.totalAmount = totalAmount;
        this.dateOfSale = dateOfSale;
        this.salesStatus = salesStatus;
        this.customer = customer;
    }

    // Getter for salesId
    public int getSalesId() {
        return salesId;
    }

    // Setter for salesId
    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    // Getter for totalAmount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Setter for totalAmount
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter for dateOfSale
    public Timestamp getDateOfSale() {
        return dateOfSale;
    }

    // Setter for dateOfSale
    public void setDateOfSale(Timestamp dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    // Getter for salesStatus
    public String getSalesStatus() {
        return salesStatus;
    }

    // Setter for salesStatus
    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }

    // Getter for customer
    public CustomerDetails getCustomer() {
        return customer;
    }

    // Setter for customer
    public void setCustomer(CustomerDetails customer) {
        this.customer = customer;
    }

    // toString method to print Sales object
    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + salesId +
                ", totalAmount=" + totalAmount +
                ", dateOfSale=" + dateOfSale +
                ", salesStatus='" + salesStatus + '\'' +
                ", customer=" + customer +
                '}';
    }
}
