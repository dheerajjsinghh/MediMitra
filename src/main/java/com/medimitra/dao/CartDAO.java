package com.medimitra.dao;

import com.medimitra.model.CartItem;
import com.medimitra.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    
    public List<CartItem> findByUserId(Long userId) throws SQLException {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT c.*, m.name, m.price, m.requires_prescription, m.stock " +
                    "FROM cart c JOIN medicines m ON c.medicine_id = m.id " +
                    "WHERE c.user_id = ? ORDER BY c.added_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getLong("id"));
                item.setUserId(rs.getLong("user_id"));
                item.setMedicineId(rs.getLong("medicine_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setMedicineName(rs.getString("name"));
                item.setMedicinePrice(rs.getBigDecimal("price"));
                item.setRequiresPrescription(rs.getBoolean("requires_prescription"));
                item.setAvailableStock(rs.getInt("stock"));
                items.add(item);
            }
        }
        return items;
    }

    public void addItem(Long userId, Long medicineId, int quantity) throws SQLException {
        String sql = "INSERT INTO cart (user_id, medicine_id, quantity) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, medicineId);
            stmt.setInt(3, quantity);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        }
    }

    public void updateQuantity(Long cartId, int quantity) throws SQLException {
        String sql = "UPDATE cart SET quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setLong(2, cartId);
            stmt.executeUpdate();
        }
    }

    public void removeItem(Long cartId) throws SQLException {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cartId);
            stmt.executeUpdate();
        }
    }

    public void clearCart(Long userId) throws SQLException {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public int getCartCount(Long userId) throws SQLException {
        String sql = "SELECT SUM(quantity) as total FROM cart WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}
