//Entity
package com.GroceryManagementCustomer.Entity;

//Import the required packages
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity // Specifies that the class is an entity and is mapped to a database table
@Table // Specifies the table in the database with which this entity is mapped
public class CustomerDetails {

    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Provides the specification of generation strategies for the primary keys
    private int customer_id; // The unique identifier for the customer

    @Column // Specifies the mapped column for a persistent property or field
    private String customer_name; // The name of the customer

    @Column // Specifies the mapped column for a persistent property or field
    private String address; // The address of the customer

    @Column // Specifies the mapped column for a persistent property or field
    private String mobile_num; // The mobile number of the customer

    // Default constructor
    public CustomerDetails() { 
    }

    // Parameterized constructor
    public CustomerDetails(String customer_name, String address, String mobile_num) {
        super();
        this.customer_name = customer_name;
        this.address = address;
        this.mobile_num = mobile_num;
    }

    // Getter for customer_id
    public int getCustomer_id() {
        return customer_id;
    }

    // Setter for customer_id
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    // Getter for customer_name
    public String getCustomer_name() {
        return customer_name;
    }

    // Setter for customer_name
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for mobile_num
    public String getMobile_num() {
        return mobile_num;
    }

    // Setter for mobile_num
    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    // Override the toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "CustomerDetails [customer_id=" + customer_id + ", customer_name=" + customer_name + ", address="
                + address + ", mobile_num=" + mobile_num + "]";
    }
}
