package com.electrotrack.dao;

import com.electrotrack.database.DBConnection;
import com.electrotrack.model.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete DAO class implementing BaseDAO for Component models.
 * Manages JDBC CRUD and Search operations for the 'components' table.
 */
public class ComponentDAO implements BaseDAO<Component> {
    
    // Helper method to convert a database row (ResultSet) into an encapsulated Component object
    private Component mapResultSetToComponent(ResultSet rs) throws SQLException {
        return new Component(
            rs.getInt("component_id"),
            rs.getString("part_number"),
            rs.getString("name"),
            rs.getString("category"),
            rs.getInt("quantity_in_stock"),
            rs.getInt("supplier_id")
        );
    }

    // 1. CREATE - Inserts a new component into the database
    @Override
    public boolean create(Component comp) {
        String sql = "INSERT INTO components (part_number, name, category, quantity_in_stock, supplier_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, comp.getPartNumber());
            stmt.setString(2, comp.getName());
            stmt.setString(3, comp.getCategory());
            stmt.setInt(4, comp.getQuantityInStock());
            
            if (comp.getSupplierId() > 0) {
                stmt.setInt(5, comp.getSupplierId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. READ ALL - Fetches all electronic components to populate your JTables
    @Override
    public List<Component> readAll() {
        List<Component> list = new ArrayList<>();
        String sql = "SELECT * FROM components";
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToComponent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. UPDATE - Modifies an existing component record
    @Override
    public boolean update(Component comp) {
        String sql = "UPDATE components SET part_number = ?, name = ?, category = ?, quantity_in_stock = ?, supplier_id = ? WHERE component_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, comp.getPartNumber());
            stmt.setString(2, comp.getName());
            stmt.setString(3, comp.getCategory());
            stmt.setInt(4, comp.getQuantityInStock());
            
            if (comp.getSupplierId() > 0) {
                stmt.setInt(5, comp.getSupplierId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            stmt.setInt(6, comp.getComponentId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. DELETE - Removes a component safely or catches relational constraints
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM components WHERE component_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // This prevents a crash when a part is linked to a transaction item row (ON DELETE RESTRICT)
            System.err.println("Database Integrity Restriction: Cannot delete component record linked to active transactions.");
            return false;
        }
    }

    // 5. SEARCH - Filters electronic components using tracking keywords
    @Override
    public List<Component> search(String keyword) {
        List<Component> list = new ArrayList<>();
        String sql = "SELECT * FROM components WHERE part_number LIKE ? OR name LIKE ? OR category LIKE ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String matchStr = "%" + keyword + "%";
            stmt.setString(1, matchStr);
            stmt.setString(2, matchStr);
            stmt.setString(3, matchStr);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToComponent(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
/**
     * Verifies if a given supplier ID exists in the database.
     * Prevents foreign key constraint crashes during item registration.
     */
    public boolean isValidSupplier(int supplierId) {
        String sql = "SELECT 1 FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if the supplier ID exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Updates the stock level quantity for an existing tracked component.
     */
    public boolean updateQuantity(int componentId, int newQty) {
        String sql = "UPDATE components SET quantity_in_stock = ? WHERE component_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, newQty);
            stmt.setInt(2, componentId);
            return stmt.executeUpdate() > 0; // Returns true if updated successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}