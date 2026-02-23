package com.example.nodebook_hub.notebook_hub_backend.DTO;

import com.example.nodebook_hub.notebook_hub_backend.Entity.UserDetails;

public class ResponseDTO {
    private String message;
    private boolean success;

    private Boolean admin;
    private UserDetails userDetails;

    private String token;
    // Constructor: message + success only
    public ResponseDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Constructor: message + success + userDetails
    public ResponseDTO(String message, boolean success, UserDetails userDetails) {
        this.message = message;
        this.success = success;
        this.userDetails = userDetails;
    }

    // Constructor: message + success + admin + userDetails
    public ResponseDTO(String message, boolean success, Boolean admin, UserDetails userDetails) {
        this.message = message;
        this.success = success;
        this.admin = admin;
        this.userDetails = userDetails;
    }

    // Constructor: message + success + token + userDetails
    public ResponseDTO(String message, boolean success, String token, UserDetails userDetails) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
