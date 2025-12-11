package com.medimitra.dao;

import com.medimitra.model.Medicine;
import com.medimitra.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    
    public List<Medicine> findAll() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE stock > 0 ORDER BY name";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
               // medicines.add(extractMedicine(rs));
            }
        }
        return medicines;
    }

    public Medicine findById(Long id) throws SQLException {
        String sql = "SELECT * FROM medicines WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractMedicine(rs);
            }
        }
        return null;
    }

    public List<Medicine> findByCategory(String category) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE category = ? AND stock > 0 ORDER BY name";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicines.add(extractMedicine(rs));
            }
        }
        return medicines;
    }

    public List<Medicine> search(String keyword) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE (name LIKE ? OR description LIKE ? OR manufacturer LIKE ?) AND stock > 0 ORDER BY name";
        String searchPattern = "%" + keyword + "%";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicines.add(extractMedicine(rs));
            }
        }
        return medicines;
    }

    public void updateStock(Long medicineId, int quantity) throws SQLException {
        String sql = "UPDATE medicines SET stock = stock - ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setLong(2, medicineId);
            stmt.executeUpdate();
        }
    }

    public Medicine create(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO medicines (name, description, manufacturer, category, price, stock, requires_prescription, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDescription());
           // stmt.setString(3, medicine.getManufacturer());
           // stmt.setString(4, medicine.getCategory());
            stmt.setBigDecimal(5, medicine.getPrice());
            stmt.setInt(6, medicine.getStock());
           // stmt.setBoolean(7, medicine.isRequiresPrescription());
           // stmt.setString(8, medicine.getImageUrl());
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               // medicine.setId(rs.getLong(1));
            }
        }
        return medicine;
    }

    private Medicine extractMedicine(ResultSet rs) throws SQLException {
        Medicine medicine = new Medicine();
       // medicine.setId(rs.getLong("id"));
        medicine.setName(rs.getString("name"));
        medicine.setDescription(rs.getString("description"));
      //  medicine.setManufacturer(rs.getString("manufacturer"));
     //   medicine.setCategory(rs.getString("category"));
        medicine.setPrice(rs.getBigDecimal("price"));
        medicine.setStock(rs.getInt("stock"));
        //medicine.setRequiresPrescription(rs.getBoolean("requires_prescription"));
      //  medicine.setImageUrl(rs.getString("image_url"));

        Timestamp createdTs = rs.getTimestamp("created_at");
        if (createdTs != null) {
           // medicine.setCreatedAt(createdTs.toLocalDateTime());
        }
        Timestamp updatedTs = rs.getTimestamp("updated_at");
        if (updatedTs != null) {
            //medicine.setUpdatedAt(updatedTs.toLocalDateTime());
        }

        return medicine;
    }
}
