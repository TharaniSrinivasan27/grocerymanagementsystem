package com.GroceryManagementUser.DAO;

//Import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.GroceryManagementUser.entity.User;
import com.GroceryManagementUser.repository.UserRepository;

/**
 * UserDAO class that provides data access functionalities for User entities.
 * It acts as an intermediary between the service layer and the repository layer.
 */
@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;
     //Save a new user to the database.
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Validate user credentials by finding a user with the given name, password, and role.
     
    public User findByNameAndPasswordAndRole(String name, String password, String role) {
        System.out.println("Finding user with name: " + name + ", password: " + password + ", role: " + role);
        return userRepository.findByNameAndPasswordAndRole(name, password, role);
    }

     //Check if a user exists by their ID.
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    // Delete a user by their ID.
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
