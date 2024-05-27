package com.GroceryManagementProduct.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import com.GroceryManagementProduct.entity.Product;
import com.GroceryManagementProduct.repository.ProductRepository;

@Component
public class ProductDAOImpl {
    @Autowired
    private final ProductRepository productRepository;

    // Constructor injection of ProductRepository dependency
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method to retrieve all product details
    public List<Product> getAllProductDetails() {
        return productRepository.findAll();
    }

    // Method to create a new product
    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    // Method to retrieve a product by its ID
    public Product getProductByProductid(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    // Method to update product name
    public Product updateProductName(Integer id, String productName) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProduct_name(productName);
            return productRepository.save(product);
        }
        return null;
    }

    // Method to update product price
    public Product updateProductPrice(Integer id, Integer price) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setPrice(price);
            return productRepository.save(product);
        }
        return null;
    }

    // Method to update product availability
    public Product updateProductAvailability(Integer id, String availability) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setAvailability(availability);
            return productRepository.save(product);
        }
        return null;
    }

    // Method to delete a product by its ID
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
        }
    }
}
