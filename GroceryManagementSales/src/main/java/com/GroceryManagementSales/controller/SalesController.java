package com.GroceryManagementSales.controller;

import com.GroceryManagementSales.entity.Sales;
import com.GroceryManagementSales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
	
	// Autowire the SalesService to use its methods
	@Autowired
	private final SalesService salesService;

	// Constructor-based dependency injection for SalesService
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }
    
    // Endpoint to get all sales records
    @GetMapping("/getall")
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> salesList = salesService.getAllSales();
        return ResponseEntity.ok(salesList);
    }
    
    // Endpoint to get a specific sales record by ID
    @GetMapping("/get/{salesId}")
    public ResponseEntity<Sales> getSalesById(@PathVariable int salesId) {
        Sales sales = salesService.getSalesById(salesId);
        if (sales != null) {
            return ResponseEntity.ok(sales);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Endpoint to create a new sales record
    @PostMapping("/create")
    public ResponseEntity<Sales> createSales(@RequestBody int sales) {
        Sales createdSales = salesService.createSales(sales);
        return new ResponseEntity<>(createdSales, HttpStatus.CREATED);
    }
    
    // Endpoint to update an existing sales record by ID
    @PutMapping("/update/{salesId}")
    public ResponseEntity<Sales> updateSales(@PathVariable int salesId, @RequestBody Sales sales) {
        try {
            Sales updatedSales = salesService.updateSales(salesId, sales);
            return ResponseEntity.ok(updatedSales);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Endpoint to delete a sales record by ID
    @DeleteMapping("/delete/{salesId}")
    public ResponseEntity<Sales> deleteSales(@PathVariable int salesId) {
        boolean deleted = salesService.deleteSales(salesId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
