package com.medimitra.model;

import java.sql.Timestamp;

public class CartItem {
    private int cartItemId;
    private int userId;
    private int medId;
    private int qty;
    private Timestamp addedAt;
    
    private Medicine medicine;

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
    }
}
