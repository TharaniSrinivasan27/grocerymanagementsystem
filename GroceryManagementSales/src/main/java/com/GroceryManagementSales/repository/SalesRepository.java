package com.GroceryManagementSales.repository;

import com.GroceryManagementSales.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
    // This interface extends JpaRepository to provide CRUD operations for the Sales entity.
    // By extending JpaRepository, we inherit methods to perform basic CRUD operations without any implementation.
    // The Sales entity type and the primary key type (Integer) are specified as generic parameters.
}