package com.GroceryManagementUser.controller;

//Import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GroceryManagementUser.entity.User;
import com.GroceryManagementUser.service.UserService;

/**
 * UserController class that handles HTTP requests for User entities.
 * It provides endpoints for creating, validating, and deleting users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Endpoint to create a new user.
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint to validate user credentials.
 
    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody User user) {
        System.out.println("Received request to validate user: " + user.getName());
        User validatedUser = userService.validateUser(user.getName(), user.getPassword(), user.getRole());
        if (validatedUser != null) {
            return ResponseEntity.ok("User validated successfully!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }

    //Endpoint to delete a user by id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
}
