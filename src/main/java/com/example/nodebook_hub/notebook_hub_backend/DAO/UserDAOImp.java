package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.Config.JwtUtil;
import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.hibernate.query.Order;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ResponseDTO save(UserDetails user){
        try {
            entityManager.persist(user);

            return new ResponseDTO("User saved successfully",true);
        }
        catch(Exception e){

            return new ResponseDTO("Failed to save user.", false);
        }
    }
    @Transactional
    public ResponseDTO validateCredentials(String email,String password){
        try {
            TypedQuery<UserDetails> query = entityManager.createQuery("select u from UserDetails u where u.email=:email", UserDetails.class); //:email is a placeholder that is replaced below
            query.setParameter("email", email);//We are passing the parameters by replacing :email = email value;
            UserDetails user;
            try {
                user = query.getSingleResult();  // Fetch user details
            } catch (Exception e) {
                return new ResponseDTO("Invalid email or password", false);
            }
            if (user.getPassword().equals(password)) {
                ResponseDTO response = new ResponseDTO("Login Successful", true,user);
                response.setAdmin(user.getAdmin());
                response.setUserDetails(user);
                return response;
            } else {
                return new ResponseDTO("Invalid email or password", false);
            }
        }
        catch(Exception e){
            return new ResponseDTO("Authentication failed",false);
        }

    }
        public List<NoteBookDetails> getAllNotebooks(){
            TypedQuery<NoteBookDetails> q=entityManager.createQuery("select u from NoteBookDetails u",NoteBookDetails.class);
            List<NoteBookDetails> result=q.getResultList(); //To get multiple list;
            return result.isEmpty() ? Collections.emptyList() : result;
        }
        @Transactional
        public ResponseDTO saveOrderDetails(OrderDetails orderDetails){

            NoteBookDetails product = entityManager.find(NoteBookDetails.class, orderDetails.getProduct().getNote_id());

            if (product == null) {
                return new ResponseDTO("Product not found", false);
            }

            // Check if enough stock is available
            if (product.getQuantity() < orderDetails.getQuantity()) {
                return new ResponseDTO("Insufficient stock", false);
            }

            // Reduce the stock
            int updatedQuantity = product.getQuantity() - orderDetails.getQuantity();
            product.setQuantity(updatedQuantity);

            // Update the product entity
            entityManager.merge(product);

            // Save the order
            entityManager.persist(orderDetails);
            AdminAnalytics analytics = entityManager.find(AdminAnalytics.class, 1L); // use appropriate ID logic if needed

            if (analytics != null) {
                // Update analytics
                analytics.setTotalBookings(analytics.getTotalBookings() + 1);
                analytics.setNotebookSold(analytics.getNotebookSold() + orderDetails.getQuantity());
                analytics.setTotalRevenue( analytics.getTotalRevenue().add(orderDetails.getTotalPrice()));
                analytics.setTotalStock(analytics.getTotalStock() - orderDetails.getQuantity());
                analytics.setPendingOrders(analytics.getPendingOrders() + 1); // optional

                entityManager.merge(analytics);
            }

            return new ResponseDTO("Order saved successfully", true);
        }
    public UserDetails findByUserId(int user_id){
        System.out.println("ID"+user_id);
        TypedQuery<UserDetails> query=entityManager.createQuery("select u from UserDetails u where u.user_id=:user_id",UserDetails.class);
        query.setParameter("user_id",user_id);
        UserDetails user=query.getSingleResult();
        return user;
    }
    public NoteBookDetails findByProductId(int note_id){
        TypedQuery<NoteBookDetails> query=entityManager.createQuery("select u from NoteBookDetails u where u.note_id=:note_id",NoteBookDetails.class);
        query.setParameter("note_id",note_id);
        NoteBookDetails noteBookDetails=query.getSingleResult();
        return noteBookDetails;
    }
    public List<OrderDetails> getOrdersByUserId(int user_id){
        String jpql = "SELECT o FROM OrderDetails o WHERE o.user.id = :user_id";

        return entityManager.createQuery(jpql, OrderDetails.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }
    public List<CustomOrder> getCustomOrdersByUserId(int user_id){
        String jpql = "SELECT o FROM CustomOrder o WHERE o.user.id = :user_id";

        return entityManager.createQuery(jpql, CustomOrder.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }
    @Transactional
    public void saveCustomOrder(CustomOrder order){
        entityManager.persist(order);
    }
}
