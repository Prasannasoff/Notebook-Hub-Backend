package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

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



}
