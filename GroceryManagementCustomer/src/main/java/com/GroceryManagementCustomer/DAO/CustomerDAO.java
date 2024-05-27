//DAO
package com.GroceryManagementCustomer.DAO;

//import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.GroceryManagementCustomer.Entity.CustomerDetails;
import com.GroceryManagementCustomer.repository.CustomerRepository;
import java.util.List;

@Repository // Marks this class as a Spring repository
public class CustomerDAO {

    @Autowired // Automatically injects the CustomerRepository bean
    private CustomerRepository repository;

    // Method to get all customer details
    public List<CustomerDetails> getAllCustomerDetails() {
        // Calls the repository to find all customers
        return repository.findAll();
    }

    // Method to create a new customer
    public void createCustomer(CustomerDetails customer) {
        // Calls the repository to save the new customer
        repository.save(customer);
    }

    // Method to get customer details by ID
    public CustomerDetails getCustomerDetailsById(int id) {
        // Calls the repository to find a customer by ID, returns null if not found
        return repository.findById(id).orElse(null);
    }

    // Method to update customer details
    public CustomerDetails updateCustomer(CustomerDetails updatedCustomer) {
        // Calls the repository to save the updated customer details
        return repository.save(updatedCustomer);
    }

    // Method to delete (close) a customer by ID
    public void closeCustomer(int id) {
        // Calls the repository to find a customer by ID
        CustomerDetails customer = repository.findById(id).orElse(null);
        if (customer != null) {
            // Calls the repository to delete the customer if found
            repository.delete(customer);
        } else {
            // Throws an exception if the customer is not found
            throw new RuntimeException("Customer with ID " + id + " not found");
        }
    }
}


