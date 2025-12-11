package com.medimitra.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Coupon {
    private int couponId;
    private String code;
    private CouponType type;
    private BigDecimal value;
    private BigDecimal minPurchase;
    private BigDecimal maxDiscount;
    private Date validFrom;
    private Date validTo;
    private int maxUses;
    private int usesCount;
    private boolean active;
    private String allowedCategories;
    private String description;
    private Timestamp createdAt;

    public enum CouponType {
        FLAT, PERCENTAGE
    }

    public Coupon() {
        this.minPurchase = BigDecimal.ZERO;
        this.maxUses = 1000;
        this.usesCount = 0;
        this.active = true;
    }

    public boolean isValid() {
        if (!active) return false;
        if (usesCount >= maxUses) return false;
        
        Date today = new Date(System.currentTimeMillis());
        if (validFrom != null && today.before(validFrom)) return false;
        if (validTo != null && today.after(validTo)) return false;
        
        return true;
    }

    public BigDecimal calculateDiscount(BigDecimal subtotal) {
        if (!isValid() || subtotal.compareTo(minPurchase) < 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal discount;
        if (type == CouponType.FLAT) {
            discount = value;
        } else {
            discount = subtotal.multiply(value).divide(new BigDecimal("100"));
        }

        if (maxDiscount != null && discount.compareTo(maxDiscount) > 0) {
            discount = maxDiscount;
        }

        return discount;
    }

    // Getters and Setters
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(BigDecimal minPurchase) {
        this.minPurchase = minPurchase;
    }

    public BigDecimal getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(BigDecimal maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public int getUsesCount() {
        return usesCount;
    }

    public void setUsesCount(int usesCount) {
        this.usesCount = usesCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAllowedCategories() {
        return allowedCategories;
    }

    public void setAllowedCategories(String allowedCategories) {
        this.allowedCategories = allowedCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
