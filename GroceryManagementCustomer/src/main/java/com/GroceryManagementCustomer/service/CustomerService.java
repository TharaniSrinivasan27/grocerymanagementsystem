//service
package com.GroceryManagementCustomer.service;

//import the required packages
import com.GroceryManagementCustomer.DAO.CustomerDAO;
import com.GroceryManagementCustomer.Entity.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marks this class as a Spring service component
public class CustomerService {
    
    @Autowired // Automatically injects the CustomerDAO bean
    CustomerDAO customerDAO;

    // Method to create a new customer
    public CustomerDetails createCustomer(CustomerDetails customer) {
        // Calls the DAO to create a new customer
        customerDAO.createCustomer(customer);
        return customer; // Returns the created customer details
    }

    // Method to get customer details by ID
    public CustomerDetails getCustomerDetailsById(int id) {
        // Calls the DAO to get customer details by ID
        return customerDAO.getCustomerDetailsById(id);
    }

    // Method to get all customer details
    public List<CustomerDetails> getAllCustomerDetails() {
        // Calls the DAO to get all customer details
        return customerDAO.getAllCustomerDetails();
    }

    // Method to update customer details
    public CustomerDetails updateCustomer(int id, CustomerDetails updatedCustomer) {
        // Calls the DAO to get existing customer details by ID
        CustomerDetails existingCustomer = customerDAO.getCustomerDetailsById(id);
        if (existingCustomer != null) {
            // Updates the existing customer details with the new values
            existingCustomer.setCustomer_name(updatedCustomer.getCustomer_name());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setMobile_num(updatedCustomer.getMobile_num());
            // Calls the DAO to update the customer details and returns the updated details
            return customerDAO.updateCustomer(existingCustomer);
        } else {
            // Throws an exception if the customer is not found
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
    }

    // Method to delete (close) a customer
    public void closeCustomer(int id) {
        // Calls the DAO to get customer details by ID
        CustomerDetails customer = customerDAO.getCustomerDetailsById(id);
        if (customer != null) {
            // Calls the DAO to delete the customer if found
            customerDAO.closeCustomer(id);
        } else {
            // Throws an exception if the customer is not found
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
    } 
}


