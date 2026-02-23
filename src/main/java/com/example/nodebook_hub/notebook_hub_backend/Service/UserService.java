package com.example.nodebook_hub.notebook_hub_backend.Service;

import com.example.nodebook_hub.notebook_hub_backend.Config.JwtUtil;
import com.example.nodebook_hub.notebook_hub_backend.DAO.UserDAO;
import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.CustomOrder;
import com.example.nodebook_hub.notebook_hub_backend.Entity.NoteBookDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.OrderDetails;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserDAO userDAO;
    private JwtUtil jwtUtil;
    @Autowired
    public UserService(UserDAO userDAO,JwtUtil jwtUtil){
        this.userDAO=userDAO;
        this.jwtUtil=jwtUtil;
    }


    public ResponseDTO save(UserDetails user){
        return userDAO.save(user);
    }
    public ResponseDTO validate(String email,String password){
        ResponseDTO response=userDAO.validateCredentials(email,password);
        if(response.isSuccess()){
            String token = jwtUtil.generateToken(email,response.getAdmin());
            return new ResponseDTO("Login successfull",true,token, response.getUserDetails());
        }
        else{
            return new ResponseDTO("Login Failed",false);
        }
    }

    public List<NoteBookDetails> getAllNoteBooks(){
        return userDAO.getAllNotebooks();
    }
    public ResponseDTO saveOrderDetails(OrderDetails orderDetails){
        return userDAO.saveOrderDetails(orderDetails);
    }
    public UserDetails findByUserId(int id){
        return userDAO.findByUserId(id);
    }
    public NoteBookDetails findByProductId(int id){
        return userDAO.findByProductId(id);
    }
    public List<OrderDetails> getOrdersByUserId(int id){
        return userDAO.getOrdersByUserId(id);
    }
    public String saveCustomOrder(CustomOrder order){
        userDAO.saveCustomOrder(order);
        return "Saved Successfully";
    }
    public List<CustomOrder> getCustomOrdersByUserId(int user_id){
        return userDAO.getCustomOrdersByUserId(user_id);
    }

}
