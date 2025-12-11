package com.medimitra.dao;

import com.medimitra.model.CartItem;
import com.medimitra.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    
    public List<CartItem> findByUserId(int userId) throws SQLException {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT c.*, m.name, m.price, m.prescription_required, m.stock " +
                    "FROM cart c JOIN medicines m ON c.med_id = m.med_id " +
                    "WHERE c.user_id = ? ORDER BY c.added_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("cart_item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setMedicineId(rs.getInt("med_id"));
                item.setQuantity(rs.getInt("qty"));
                item.setMedicineName(rs.getString("name"));
                item.setMedicinePrice(rs.getBigDecimal("price"));
                item.setRequiresPrescription(rs.getBoolean("prescription_required"));
                item.setAvailableStock(rs.getInt("stock"));
                items.add(item);
            }
        }
        return items;
    }

    public void addItem(int userId, int medicineId, int quantity) throws SQLException {
        String sql = "INSERT INTO cart (user_id, med_id, qty) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE qty = qty + ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, medicineId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        }
    }

    public void updateQuantity(int cartId, int quantity) throws SQLException {
        String sql = "UPDATE cart SET qty = ? WHERE cart_item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);
            stmt.executeUpdate();
        }
    }

    public void removeItem(int cartId) throws SQLException {
        String sql = "DELETE FROM cart WHERE cart_item_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        }
    }

    public void clearCart(int userId) throws SQLException {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    public int getCartCount(int userId) throws SQLException {
        String sql = "SELECT SUM(qty) as total FROM cart WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}
