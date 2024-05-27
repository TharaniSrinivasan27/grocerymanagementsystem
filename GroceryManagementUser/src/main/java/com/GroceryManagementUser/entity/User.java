package com.GroceryManagementUser.entity;
//Import the required packages
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    // Primary key field with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Username, must be unique and not null
    @Column(nullable = false, unique = true)
    private String name;

    // Password, must not be null
    @Column(nullable = false)
    private String password;

    // Role of the user, must not be null
    @Column(nullable = false)
    private String role;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Setter for role
    public void setRole(String role) {
        this.role = role;
    }
}
