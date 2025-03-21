package com.example.nodebook_hub.notebook_hub_backend.Service;

import com.example.nodebook_hub.notebook_hub_backend.DAO.UserDAO;
import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO=userDAO;
    }
    public ResponseDTO save(UserDetails user){
        return userDAO.save(user);
    }

}
