package com.example.nodebook_hub.notebook_hub_backend.Service;

import com.example.nodebook_hub.notebook_hub_backend.DAO.AdminDAO;

import com.example.nodebook_hub.notebook_hub_backend.Entity.AdminAnalytics;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {
    private AdminDAO adminDAO;
    @Autowired
    public AdminService(AdminDAO adminDAO){
        this.adminDAO=adminDAO;
    }
    public void saveNotebook(NoteBookDetails note){
        adminDAO.saveNotebook(note);
    }
    public List<OrderDetails> getAllOrders(){
        return adminDAO.getAllOrders();
    }
    public List<CustomOrder> getAllCustomOrders(){
        return adminDAO.getAllCustomOrders();
    }
    public OrderDetails updateOrderStatus(int orderId, String statusType) {
        OrderDetails order = adminDAO.findOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        switch (statusType) {
            case "processingDate":
                order.setProcessingDate(LocalDateTime.now());
                break;
            case "shippedDate":
                order.setShippedDate(LocalDateTime.now());
                break;
            case "deliveredDate":
                order.setDeliveredDate(LocalDateTime.now());
                break;
            default:
                throw new IllegalArgumentException("Invalid status type");
        }

        return adminDAO.save(order);
    }
    public CustomOrder updateCustomOrderStatus(int orderId, String statusType) {
        CustomOrder order = adminDAO.findCustomOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        switch (statusType) {
            case "processingDate":
                order.setProcessingDate(LocalDateTime.now());
                break;
            case "shippedDate":
                order.setShippedDate(LocalDateTime.now());
                break;
            case "deliveredDate":
                order.setDeliveredDate(LocalDateTime.now());
                break;
            default:
                throw new IllegalArgumentException("Invalid status type");
        }

        return adminDAO.updateCustomOrder(order);
    }
    public AdminAnalytics getAnalytics(){
        return adminDAO.getAnalytics();
    }

}
