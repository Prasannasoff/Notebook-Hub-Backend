package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;

import java.util.List;

public interface UserDAO {
    public ResponseDTO save(UserDetails user);
    public ResponseDTO validateCredentials(String email,String password);

    public List<NoteBookDetails> getAllNotebooks();
    public ResponseDTO saveOrderDetails(OrderDetails orderDetails);
    public UserDetails findByUserId(int user_id);
    public NoteBookDetails findByProductId(int note_id);
    public List<OrderDetails> getOrdersByUserId(int id);
    public void saveCustomOrder(CustomOrder order);
    public List<CustomOrder> getCustomOrdersByUserId(int user_id);
}
