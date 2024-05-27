//Repository
package com.GroceryManagementCustomer.repository;
//import the required packages
import com.GroceryManagementCustomer.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface for CustomerRepository which extends JpaRepository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {
}
