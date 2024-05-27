package com.GroceryManagementSales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.GroceryManagementSales.DAO.SalesDAO;
import com.GroceryManagementSales.entity.Sales;
import com.GroceryManagementCustomer.Entity.CustomerDetails;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SalesService {

    // Autowire the SalesDAO for database operations
    @Autowired
    private final SalesDAO salesDao;

    // Autowire the RestTemplate for calling external services
    @Autowired
    private RestTemplate restTemplate;

    // Base URL for the customer service
    private final String customerServiceUrl = "http://localhost:8089/customer";

    // Constructor-based dependency injection for SalesDAO
    public SalesService(SalesDAO salesDao) {
        this.salesDao = salesDao;
        this.restTemplate = new RestTemplate();
    }

    // Fetch all sales records
    public List<Sales> getAllSales() {
        return salesDao.getAllSales();
    }

    // Fetch a sales record by ID
    public Sales getSalesById(int salesId) {
        return salesDao.getSalesById(salesId);
    }

    // Create a new sales record for a given customer ID
    public Sales createSales(int customerId) {
        Sales sales = new Sales();

        // Fetch the customer details by ID
        CustomerDetails customerDetails = fetchCustomerById(customerId);

        // Set the sales details
        sales.setCustomer(customerDetails);
        sales.setSalesStatus("PENDING");
        sales.setDateOfSale(new Timestamp(System.currentTimeMillis()));
        sales.setTotalAmount(0);

        // Save and return the sales record
        return salesDao.createSales(sales);
    }

    // Update an existing sales record by ID
    public Sales updateSales(int salesId, Sales sales) {
        Sales existingSales = salesDao.getSalesById(salesId);

        if (existingSales == null) {
            throw new RuntimeException("Sales with ID " + salesId + " not found.");
        }

        // Update the sales details
        existingSales.setCustomer(sales.getCustomer());
        existingSales.setSalesStatus(sales.getSalesStatus());
        existingSales.setDateOfSale(new Timestamp(System.currentTimeMillis()));
        existingSales.setTotalAmount(sales.getTotalAmount());

        // Save and return the updated sales record
        return salesDao.updateSales(salesId, existingSales);
    }

    // Update the total amount for a sales record
    public void updateSalesTotalAmount(int salesId, Sales sales) {
        salesDao.updateSales(salesId, sales);
    }

    // Delete a sales record by ID
    public boolean deleteSales(int salesId) {
        return salesDao.deleteSales(salesId);
    }

    // Cancel a sales record by setting its status to "CANCELLED"
    public Sales cancelSales(int salesId) {
        Sales sales = salesDao.getSalesById(salesId);

        if (sales == null) {
            throw new RuntimeException("Sales with ID " + salesId + " not found.");
        }

        // Update the sales status to "CANCELLED"
        sales.setSalesStatus("CANCELLED");

        // Save and return the updated sales record
        return salesDao.updateSales(salesId, sales);
    }

    // Fetch customer details from the customer service by customer ID
    private CustomerDetails fetchCustomerById(int customerId) {
        String getCustomerByIdUrl = customerServiceUrl + "/getbyid/" + customerId;
        return restTemplate.getForObject(getCustomerByIdUrl, CustomerDetails.class);
    }
}
