package com.electrotrack.model;

/**
 * Entity model class representing a row from the 'users' table.
 * Demonstrates Object-Oriented Encapsulation via private fields.
 */
public class User {
    // Private fields prevent external classes from tampering with data directly
    private int userId;
    private String username;
    private String password;
    private String role;

    // Parameterized Constructor to initialize all values when reading from the database
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Public Getters and Setters (Provides controlled entry points to the fields)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}