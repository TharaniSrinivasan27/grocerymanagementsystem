package com.GroceryManagementProduct.controller;

// Import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.GroceryManagementProduct.entity.Product;
import com.GroceryManagementProduct.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    // Create new product
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        // Calls the service to create a product
        Product createdProduct = service.createProduct(product);
        // Returns the created product
        return createdProduct;
    }

    // Get product by product id
    @GetMapping("/{id}")
    public Product getProductByProductid(@PathVariable Integer id) {
        // Calls the service to get a product by its ID
        Product product = service.getProductByProductid(id);
        // Returns the found product
        return product;
    }

    // Get all products
    @GetMapping("/getallproducts")
    public List<Product> getAllProductDetails() {
        // Calls the service to get all products
        List<Product> allProductDetails = service.getAllProductDetails();
        // Returns the list of all products
        return allProductDetails;
    }

    // Update product name
    @PutMapping("/updateproductname/{id}/{productName}")
    public Product updateProductName(@PathVariable Integer id, @PathVariable String productName) {
        // Calls the service to update the product name
        Product updateName = service.updateProductName(id, productName);
        // Returns the updated product
        return updateName;
    }

    // Update product price
    @PutMapping("/updateproductprice/{id}/{price}")
    public Product updateProductPrice(@PathVariable Integer id, @PathVariable Integer price) {
        // Calls the service to update the product price
        Product updatePrice = service.updateProductPrice(id, price);
        // Returns the updated product
        return updatePrice;
    }

    // Update product availability
    @PutMapping("/updateproductavailability/{id}/{availability}")
    public Product updateProductAvailability(@PathVariable Integer id, @PathVariable String availability) {
        // Calls the service to update the product availability
        Product updateAvailability = service.updateProductAvailability(id, availability);
        // Returns the updated product
        return updateAvailability;
    }

    // Delete the product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        // Calls the service to delete the product
        service.deleteProduct(id);
        // Returns a confirmation message
        return ResponseEntity.ok("Product deleted");
    }

    // Get products by list of product IDs
    @GetMapping("/getSelectedProducts")
    public List<Product> getSelectedProducts(@RequestParam List<Integer> ids) {
        // Creates an empty list to store the selected products
        List<Product> selectedProducts = new ArrayList<>();
        // Iterates over the list of product IDs
        for (Integer id : ids) {
            // Calls the service to get a product by its ID
            Product product = service.getProductByProductid(id);
            // If the product is found, adds it to the list
            if (product != null) {
                selectedProducts.add(product);
            }
        }
        // Returns the list of selected products
        return selectedProducts;
    }
}
