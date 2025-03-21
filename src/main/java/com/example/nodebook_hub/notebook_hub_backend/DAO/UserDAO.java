package com.example.nodebook_hub.notebook_hub_backend.DAO;

import com.example.nodebook_hub.notebook_hub_backend.DTO.ResponseDTO;
import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;

public interface UserDAO {
    public ResponseDTO save(UserDetails user);
}
