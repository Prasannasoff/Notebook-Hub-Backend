package com.example.nodebook_hub.notebook_hub_backend.DTO;

public class StatusUpdateRequest {
    private String status;
    public StatusUpdateRequest() {} // Required by Jackson

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
