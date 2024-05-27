package com.GroceryManagementSales.controller;
//import the required packages
import com.GroceryManagementSales.entity.SalesDetails;
import com.GroceryManagementSales.service.SalesDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing sales details.
 */
@RestController
@RequestMapping("/salesdetails")
public class SalesDetailsController {

    // Service to handle sales details operations
    @Autowired
    private final SalesDetailsService salesDetailsService;

    /**
     * Constructor for SalesDetailsController.
     * @param salesDetailsService the sales details service to be used
     */
    public SalesDetailsController(SalesDetailsService salesDetailsService) {
        this.salesDetailsService = salesDetailsService;
    }

    /**
     * Retrieve all sales details.
     * @return a response entity containing the list of all sales details
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<SalesDetails>> getAllSalesDetails() {
        List<SalesDetails> salesDetailsList = salesDetailsService.getAllSalesDetails();
        return ResponseEntity.ok(salesDetailsList);
    }

    /**
     * Retrieve sales details by its ID.
     * @param salesDetailsId the ID of the sales details
     * @return a response entity containing the sales details or not found status
     */
    @GetMapping("/get/{salesDetailsId}")
    public ResponseEntity<SalesDetails> getSalesDetailsById(@PathVariable int salesDetailsId) {
        SalesDetails salesDetails = salesDetailsService.getSalesDetailsById(salesDetailsId);
        if (salesDetails != null) {
            return ResponseEntity.ok(salesDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create new sales details.
     * @param salesId the ID of the sales
     * @param productId the ID of the product
     * @param quantity the quantity of the product sold
     * @return a response entity containing the created sales details or bad request status
     */
    @PostMapping("/create")
    public ResponseEntity<SalesDetails> createSalesDetails(
            @RequestParam int salesId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        SalesDetails salesDetails = salesDetailsService.createSalesDetails(salesId, productId, quantity);

        if (salesDetails != null) {
            return new ResponseEntity<>(salesDetails, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update existing sales details by its ID.
     * @param salesDetailsId the ID of the sales details to be updated
     * @param salesId the ID of the sales
     * @param productId the ID of the product
     * @param quantity the quantity of the product sold
     * @return a response entity containing the updated sales details or not found status
     */
    @PutMapping("/update/{salesDetailsId}")
    public ResponseEntity<SalesDetails> updateSalesDetails(
            @PathVariable int salesDetailsId,
            @RequestParam int salesId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        SalesDetails updatedSalesDetails = salesDetailsService.updateSalesDetails(salesDetailsId, salesId, productId, quantity);

        if (updatedSalesDetails != null) {
            return ResponseEntity.ok(updatedSalesDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete sales details by its ID.
     * @param salesDetailsId the ID of the sales details to be deleted
     * @return a response entity with no content status if deleted, or not found status
     */
    @DeleteMapping("/delete/{salesDetailsId}")
    public ResponseEntity<Void> deleteSalesDetails(@PathVariable int salesDetailsId) {
        boolean deleted = salesDetailsService.deleteSalesDetails(salesDetailsId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieve sales details by sales ID.
     * @param salesId the ID of the sales
     * @return a response entity containing the list of sales details for the given sales ID
     */
    @GetMapping("/get-by-sales-id/{salesId}")
    public ResponseEntity<List<SalesDetails>> getSalesDetailsBySalesId(@PathVariable int salesId) {
        List<SalesDetails> salesDetails = salesDetailsService.getSalesDetailsBySalesId(salesId);
        return ResponseEntity.ok(salesDetails);
    }
}
