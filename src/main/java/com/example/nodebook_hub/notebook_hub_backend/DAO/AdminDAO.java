package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.Entity.AdminAnalytics;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface AdminDAO {
    public void saveNotebook(NoteBookDetails note);
    public List<OrderDetails> getAllOrders();
    public List<CustomOrder> getAllCustomOrders();
    public OrderDetails findOrderById(int order_id);
    public OrderDetails save(OrderDetails orderDetails);
    public CustomOrder findCustomOrderById(int order_id);
    public CustomOrder updateCustomOrder(CustomOrder order);
    public AdminAnalytics getAnalytics();
}
