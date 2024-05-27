package com.GroceryManagementProduct.service;

import org.springframework.stereotype.Service;
import com.GroceryManagementProduct.entity.Product;
import com.GroceryManagementProduct.DAO.ProductDAOImpl;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductDAOImpl productDAO;

    // Constructor injection of ProductDAOImpl dependency
    public ProductService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    // Method to create a new product
    public Product createProduct(Product product) {
        return productDAO.createProduct(product);
    }

    // Method to retrieve a product by its ID
    public Product getProductByProductid(Integer id) {
        Product product = productDAO.getProductByProductid(id);
        // Throw exception if product not found
        if (product == null) { 
            throw new NoSuchElementException("Product not found for ID: " + id);
        }
        return product;
    }

    // Method to retrieve all product details
    public List<Product> getAllProductDetails() {
        return productDAO.getAllProductDetails();
    }

    // Method to update product name
    public Product updateProductName(Integer id, String productName) {
        return productDAO.updateProductName(id, productName);
    }

    // Method to update product price
    public Product updateProductPrice(Integer id, Integer price) {
        return productDAO.updateProductPrice(id, price);
    }

    // Method to update product availability
    public Product updateProductAvailability(Integer id, String availability) {
        return productDAO.updateProductAvailability(id, availability);
    }

    // Method to delete a product
    public void deleteProduct(Integer id) {
        productDAO.deleteProduct(id);
    }
}
