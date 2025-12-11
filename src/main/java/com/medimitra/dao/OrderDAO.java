package com.medimitra.dao;

import com.medimitra.model.Order;
import com.medimitra.model.OrderItem;
import com.medimitra.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    
    public Order create(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_number, total_amount, payment_method, payment_status, shipping_address_id, prescription_verified) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.setString(2, order.getOrderNumber());
           // stmt.setBigDecimal(3, order.getTotalAmount());
            stmt.setString(4, order.getPaymentMethod());
           // stmt.setString(5, order.getPaymentStatus());
           // stmt.setLong(6, order.getShippingAddressId());
           // stmt.setBoolean(7, order.isPrescriptionVerified());
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               // order.setId(rs.getLong(1));
            }
        }
        return order;
    }

    public void addOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, medicine_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, item.getOrderId());
           // stmt.setLong(2, item.getMedicineId());
           // stmt.setInt(3, item.getQuantity());
           // stmt.setBigDecimal(4, item.getPrice());
           // stmt.setBigDecimal(5, item.getSubtotal());
            stmt.executeUpdate();
        }
    }

    public List<Order> findByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY placed_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        }
        return orders;
    }

    public Order findById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractOrder(rs);
            }
        }
        return null;
    }

    public List<OrderItem> findOrderItems(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*, m.name as medicine_name FROM order_items oi " +
                    "JOIN medicines m ON oi.med_id = m.med_id WHERE oi.order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                //item.setId(rs.getLong("id"));
                //item.setOrderId(rs.getLong("order_id"));
                //item.setMedicineId(rs.getLong("medicine_id"));
               // item.setQuantity(rs.getInt("quantity"));
                //item.setPrice(rs.getBigDecimal("price"));
               // item.setSubtotal(rs.getBigDecimal("subtotal"));
               // item.setMedicineName(rs.getString("medicine_name"));
                items.add(item);
            }
        }
        return items;
    }

    public void updateStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE orders SET order_status = ?, updated_at = CURRENT_TIMESTAMP WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }

    public void updatePaymentStatus(int orderId, String paymentStatus) throws SQLException {
        String sql = "UPDATE orders SET payment_status = ?, updated_at = CURRENT_TIMESTAMP WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paymentStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
       // order.setId(rs.getLong("id"));
        //order.setUserId(rs.getLong("user_id"));
        order.setOrderNumber(rs.getString("order_number"));
        //order.setTotalAmount(rs.getBigDecimal("total_amount"));
        //order.setStatus(rs.getString("status"));
        order.setPaymentMethod(rs.getString("payment_method"));
        //order.setPaymentStatus(rs.getString("payment_status"));
        //order.setShippingAddressId(rs.getLong("shipping_address_id"));
       // order.setPrescriptionVerified(rs.getBoolean("prescription_verified"));
        
        Timestamp createdTs = rs.getTimestamp("created_at");
        if (createdTs != null) {
           // order.setCreatedAt(createdTs.toLocalDateTime());
        }
        Timestamp updatedTs = rs.getTimestamp("updated_at");
        if (updatedTs != null) {
            //order.setUpdatedAt(updatedTs.toLocalDateTime());
        }
        
        return order;
    }
}

