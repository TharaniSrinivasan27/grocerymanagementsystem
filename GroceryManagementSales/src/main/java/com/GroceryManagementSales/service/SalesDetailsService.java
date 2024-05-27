package com.GroceryManagementSales.service;

//import required packages
import com.GroceryManagementSales.DAO.SalesDetailsDAO;
import com.GroceryManagementSales.entity.SalesDetails;
import com.GroceryManagementSales.entity.Sales;
import com.GroceryManagementProduct.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service class for managing sales details.
 */
@Service
public class SalesDetailsService {

    // DAO for SalesDetails
    @Autowired
    private final SalesDetailsDAO salesDetailsDao;

    // RestTemplate for calling external services
    @Autowired
    private final RestTemplate restTemplate;

    // Base URL for the product service
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8054/product";

    /**
     * Constructor for SalesDetailsService.
     *
     * @param salesDetailsDao the DAO for sales details
     * @param restTemplate the RestTemplate for calling external services
     */
    public SalesDetailsService(SalesDetailsDAO salesDetailsDao, RestTemplate restTemplate) {
        this.salesDetailsDao = salesDetailsDao;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieve all sales details.
     *
     * @return a list of all sales details
     */
    public List<SalesDetails> getAllSalesDetails() {
        return salesDetailsDao.getAllSalesDetails();
    }

    /**
     * Retrieve sales details by its ID.
     *
     * @param salesDetailsId the ID of the sales details
     * @return the sales details with the given ID
     */
    public SalesDetails getSalesDetailsById(int salesDetailsId) {
        return salesDetailsDao.getSalesDetailsById(salesDetailsId);
    }

    /**
     * Create new sales details.
     *
     * @param salesId the ID of the sales
     * @param productId the ID of the product
     * @param quantity the quantity of the product sold
     * @return the created sales details
     */
    public SalesDetails createSalesDetails(int salesId, int productId, int quantity) {
        SalesDetails salesDetails = new SalesDetails();

        // Get product information from the product service
        String productUrl = PRODUCT_SERVICE_URL + "/get-by-id/" + productId;
        Product product = restTemplate.getForObject(productUrl, Product.class);

        // Check if product is found
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        // Create and save the sales details
        salesDetails.setProduct(product);
        salesDetails.setQuantity(quantity);
        salesDetails.setCost(product.getPrice() * quantity); // Assuming product has a price attribute
        return salesDetailsDao.createSalesDetails(salesDetails);
    }

    /**
     * Update existing sales details by its ID.
     *
     * @param salesDetailsId the ID of the sales details to be updated
     * @param salesId the ID of the sales
     * @param productId the ID of the product
     * @param quantity the quantity of the product sold
     * @return the updated sales details
     */
    public SalesDetails updateSalesDetails(int salesDetailsId, int salesId, int productId, int quantity) {
        SalesDetails salesDetails = salesDetailsDao.getSalesDetailsById(salesDetailsId);

        // Check if sales details is found
        if (salesDetails == null) {
            throw new RuntimeException("Sales details with ID " + salesDetailsId + " not found.");
        }

        // Get product information from the product service
        String productUrl = PRODUCT_SERVICE_URL + "/get-by-id/" + productId;
        Product product = restTemplate.getForObject(productUrl, Product.class);

        // Check if product is found
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        // Update the sales details
        salesDetails.setProduct(product);
        salesDetails.setQuantity(quantity);
        salesDetails.setCost(product.getPrice() * quantity); // Assuming product has a price attribute

        return salesDetailsDao.updateSalesDetails(salesDetailsId, salesDetails);
    }

    /**
     * Delete sales details by its ID.
     *
     * @param salesDetailsId the ID of the sales details to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteSalesDetails(int salesDetailsId) {
        return salesDetailsDao.deleteSalesDetails(salesDetailsId);
    }

    /**
     * Retrieve sales details by sales ID.
     *
     * @param salesId the ID of the sales
     * @return a list of sales details for the given sales ID
     */
    public List<SalesDetails> getSalesDetailsBySalesId(int salesId) {
        return salesDetailsDao.getSalesDetailsBySalesId(salesId);
    }
}
