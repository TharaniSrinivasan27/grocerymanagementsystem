package com.GroceryManagementUser.repository;
//Import the required packages
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.GroceryManagementUser.entity.User;

/**
 * UserRepository interface for performing CRUD operations on User entities.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

     
    User findByNameAndPasswordAndRole(String name, String password, String role);

    // This interface extends JpaRepository to inherit basic CRUD operations for User entity
 
    // It also serves as a Spring bean managed by the Spring application context
}
