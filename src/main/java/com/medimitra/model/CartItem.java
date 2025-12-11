package com.medimitra.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CartItem {
    private int cartItemId;
    private int userId;
    private int medId;
    private int qty;
    private Timestamp addedAt;
    
    private Medicine medicine;
    // Additional fields for display
    private String medicineName;
    private BigDecimal medicinePrice;
    private boolean requiresPrescription;
    private int availableStock;

    public CartItem() {
        this.qty = 1;
    }

    public CartItem(int userId, int medId, int qty) {
        this.userId = userId;
        this.medId = medId;
        this.qty = qty;
    }

    // Getters and Setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    // Alias for getId()
    public int getId() {
        return cartItemId;
    }

    public void setId(int id) {
        this.cartItemId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    // Alias for getMedicineId()
    public int getMedicineId() {
        return medId;
    }

    public void setMedicineId(int medicineId) {
        this.medId = medicineId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    // Alias for getQuantity()
    public int getQuantity() {
        return qty;
    }

    public void setQuantity(int quantity) {
        this.qty = quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        if (medicine != null) {
            this.medicineName = medicine.getName();
            this.medicinePrice = medicine.getPrice();
            this.requiresPrescription = medicine.isPrescriptionRequired();
        }
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public BigDecimal getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(BigDecimal medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    // Calculate subtotal
    public BigDecimal getSubtotal() {
        if (medicinePrice != null) {
            return medicinePrice.multiply(new BigDecimal(qty));
        }
        return BigDecimal.ZERO;
    }
}
