package com.electrotrack.dao;

import com.electrotrack.database.DBConnection;
import com.electrotrack.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete DAO managing user authentication and account creation workflows.
 */
public class UserDAO implements BaseDAO<User> {

    // 1. CREATE - Registers a brand new user account into MySQL
    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); 
            stmt.setString(3, user.getRole());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. AUTHENTICATE - Validates user login attempts
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Unused interface requirements needed to maintain project compilation structures
    @Override
    public List<User> readAll() { return new ArrayList<>(); }
    @Override
    public boolean update(User obj) { return false; }
    @Override
    public boolean delete(int id) { return false; }
    @Override
    public List<User> search(String keyword) { return new ArrayList<>(); }
}