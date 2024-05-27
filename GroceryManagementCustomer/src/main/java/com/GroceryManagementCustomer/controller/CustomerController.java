//Controller
package com.GroceryManagementCustomer.controller;
//import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GroceryManagementCustomer.service.CustomerService;
import com.GroceryManagementCustomer.Entity.CustomerDetails;
import java.util.List;

@RestController // Marks this class as a RESTful controller
@RequestMapping("/customer") // Maps HTTP requests to /customer to methods in this controller

public class CustomerController {

    @Autowired // Automatically injects the CustomerService bean
    CustomerService service;

    // Create a new customer
    @PostMapping("/create") // Maps HTTP POST requests to /create to this method
    public ResponseEntity<CustomerDetails> createCustomer(@RequestBody CustomerDetails customer) {
        // Calls the service to create a new customer
        CustomerDetails createdCustomer = service.createCustomer(customer);
        // Returns a ResponseEntity with the created customer and a HTTP status code of 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // Get customer by ID
    @GetMapping("/{id}") // Maps HTTP GET requests to /{id} to this method
    public ResponseEntity<CustomerDetails> getCustomerDetailsById(@PathVariable Integer id) {
        // Calls the service to get customer details by ID
        CustomerDetails customer = service.getCustomerDetailsById(id);
        if (customer != null) {
            // Returns the customer details with a HTTP status code of 200 (OK)
            return ResponseEntity.ok(customer);
        } else {
            // Returns a HTTP status code of 404 (Not Found) if customer is not found
            return ResponseEntity.notFound().build();
        }
    }

    // Get all customers
    @GetMapping("/getall") // Maps HTTP GET requests to /getall to this method
    public ResponseEntity<List<CustomerDetails>> getAllCustomerDetails() {
        // Calls the service to get all customer details
        List<CustomerDetails> allCustomers = service.getAllCustomerDetails();
        // Returns the list of all customers with a HTTP status code of 200 (OK)
        return ResponseEntity.ok(allCustomers);
    }

    // Update customer details
    @PutMapping("/update/{id}") // Maps HTTP PUT requests to /update/{id} to this method
    public ResponseEntity<CustomerDetails> updateCustomerDetails(
            @PathVariable Integer id, // Binds the id path variable to this parameter
            @RequestBody CustomerDetails updatedCustomer) { // Binds the request body to this parameter

        // Calls the service to update customer details
        CustomerDetails updatedCustomerResult = service.updateCustomer(id, updatedCustomer);

        if (updatedCustomerResult != null) {
            // Returns the updated customer details with a HTTP status code of 200 (OK)
            return ResponseEntity.ok(updatedCustomerResult);
        } else {
            // Returns a HTTP status code of 404 (Not Found) if customer is not found
            return ResponseEntity.notFound().build();
        }
    }

    // Delete customer
    @DeleteMapping("/delete/{id}") // Maps HTTP DELETE requests to /delete/{id} to this method
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        // Calls the service to delete (close) the customer account
        service.closeCustomer(id);
        // Returns a HTTP status code of 204 (No Content) with a confirmation message
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer account closed");
    }
}
