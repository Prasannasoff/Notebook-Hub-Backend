package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.Entity.AdminAnalytics;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAOImp implements AdminDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void saveNotebook(NoteBookDetails note){
        try {
            entityManager.persist(note);
            AdminAnalytics analytics = entityManager.find(AdminAnalytics.class, 1L); // use appropriate ID logic if needed

            if (analytics != null) {
                analytics.setTotalStock(analytics.getTotalStock()+note.getQuantity());
            }
            entityManager.merge(analytics);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    public List<OrderDetails> getAllOrders(){
        TypedQuery<OrderDetails> query=entityManager.createQuery("select u from OrderDetails u",OrderDetails.class);
        List<OrderDetails> list=query.getResultList();
        return list;
    }
    public List<CustomOrder> getAllCustomOrders(){
        TypedQuery<CustomOrder> query=entityManager.createQuery("select u from CustomOrder u", CustomOrder.class);
        List<CustomOrder> list=query.getResultList();
        return list;
    }
    public OrderDetails findOrderById(int order_id) {
        try {
            System.out.println("Finding order with ID: " + order_id);
            OrderDetails order = entityManager.find(OrderDetails.class, order_id);
            if (order == null) {
                System.out.println("Order not found for ID: " + order_id);
            } else {
                System.out.println("Order found: " + order.getOrder_id());
            }
            return order;
        } catch (Exception e) {
            System.out.println("Error while finding order: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public OrderDetails save(OrderDetails orderDetails) {
        try {
            if (orderDetails.getOrder_id() == null) {
                System.out.println("Saving new order...");
                entityManager.persist(orderDetails);
                System.out.println("New order saved with ID: " + orderDetails.getOrder_id());
                return orderDetails;
            } else {
                System.out.println("Updating existing order with ID: " + orderDetails.getOrder_id());
                OrderDetails updatedOrder = entityManager.merge(orderDetails);
                System.out.println("Order updated successfully.");
                return updatedOrder;
            }
        } catch (Exception e) {
            System.out.println("Error while saving/updating order: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public CustomOrder findCustomOrderById(int note_id) {
        try {
            System.out.println("Finding order with ID: " + note_id);
            CustomOrder order = entityManager.find(CustomOrder.class, note_id);
            if (order == null) {
                System.out.println("Order not found for ID: " + note_id);
            } else {
                System.out.println("Order found: " + order.getNote_id());
            }
            return order;
        } catch (Exception e) {
            System.out.println("Error while finding order: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public CustomOrder updateCustomOrder(CustomOrder orderDetails) {
        try {

                System.out.println("Updating existing order with ID: " + orderDetails.getNote_id());
                CustomOrder updatedOrder = entityManager.merge(orderDetails);
                System.out.println("Order updated successfully.");
                return updatedOrder;

        } catch (Exception e) {
            System.out.println("Error while saving/updating order: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public AdminAnalytics getAnalytics(){
        TypedQuery<AdminAnalytics> query=entityManager.createQuery("select u from AdminAnalytics u",AdminAnalytics.class);
        AdminAnalytics response=query.getSingleResult();
        return response;
    }


}
