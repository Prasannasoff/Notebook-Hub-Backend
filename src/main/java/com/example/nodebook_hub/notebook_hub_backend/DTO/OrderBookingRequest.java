package com.example.nodebook_hub.notebook_hub_backend.DTO;

import com.example.nodebook_hub.notebook_hub_backend.Entity.Address;

import java.math.BigDecimal;

public class OrderBookingRequest {
    private int userId;       // ID of the user placing the order
    private int productId;    // ID of the notebook being ordered
    private Integer quantity;  // Number of items being ordered
    private Address address;   // Delivery address
    private BigDecimal totalPrice;  // Total price of the order

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
