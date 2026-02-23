package com.example.nodebook_hub.notebook_hub_backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class AdminAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalStock;
    private int totalBookings;
    private BigDecimal totalRevenue;
    private int notebookSold;
    private int pendingOrders;

    public AdminAnalytics() {
    }

    public AdminAnalytics(Long id, int pendingOrders, int notebookSold, BigDecimal totalRevenue, int totalBookings, int totalStock) {
        this.id = id;
        this.pendingOrders = pendingOrders;
        this.notebookSold = notebookSold;
        this.totalRevenue = totalRevenue;
        this.totalBookings = totalBookings;
        this.totalStock = totalStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getNotebookSold() {
        return notebookSold;
    }

    public void setNotebookSold(int notebookSold) {
        this.notebookSold = notebookSold;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
