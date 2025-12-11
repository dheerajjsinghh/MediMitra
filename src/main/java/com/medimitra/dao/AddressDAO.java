package com.medimitra.dao;

import com.medimitra.model.Address;
import com.medimitra.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    
    public List<Address> findByUserId(int userId) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM addresses WHERE user_id = ? ORDER BY is_default DESC, created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                addresses.add(extractAddress(rs));
            }
        }
        return addresses;
    }

    public Address findById(int id) throws SQLException {
        String sql = "SELECT * FROM addresses WHERE address_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAddress(rs);
            }
        }
        return null;
    }

    public Address create(Address address) throws SQLException {
        String sql = "INSERT INTO addresses (user_id, address_line1, address_line2, city, state, postal_code, is_default) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getAddressLine1());
            stmt.setString(3, address.getAddressLine2());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getState());
            stmt.setString(6, address.getPostalCode());
            stmt.setBoolean(7, address.isDefault());
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                address.setId(rs.getInt(1));
            }
        }
        return address;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM addresses WHERE address_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Address extractAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        address.setUserId(rs.getInt("user_id"));
        address.setAddressLine1(rs.getString("address_line1"));
        address.setAddressLine2(rs.getString("address_line2"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setPostalCode(rs.getString("postal_code"));
        address.setDefault(rs.getBoolean("is_default"));
        return address;
    }
}
