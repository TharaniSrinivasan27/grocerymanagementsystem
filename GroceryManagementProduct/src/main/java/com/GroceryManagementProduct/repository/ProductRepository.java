//Repository
package com.GroceryManagementProduct.repository;
//Import the required packages
import org.springframework.data.jpa.repository.JpaRepository;
import com.GroceryManagementProduct.entity.Product;

/**
 * Repository interface for interacting with the Product entity in the database.
 * Extends JpaRepository which provides CRUD operations for Product entities.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
