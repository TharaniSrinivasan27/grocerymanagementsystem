package com.GroceryManagementUser.service;

//import the required packages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.GroceryManagementUser.DAO.UserDAO;
import com.GroceryManagementUser.entity.User;

/**
 * UserService class that provides business logic for User entities.
 * It acts as an intermediary between the controller layer and the data access layer (DAO).
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;
     //Save a new user to the database by delegating the call to the DAO layer.
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    // Validate user credentials by delegating the call to the DAO layer.
    
    public User validateUser(String name, String password, String role) {
        System.out.println("Validating user with name: " + name);
        User user = userDao.findByNameAndPasswordAndRole(name, password, role);
        System.out.println("Found user: " + (user != null ? user.getName() : "null"));
        return user;
    }

    //Delete a user by their ID by first checking if the user exists.
    public boolean deleteUser(int id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }
}
