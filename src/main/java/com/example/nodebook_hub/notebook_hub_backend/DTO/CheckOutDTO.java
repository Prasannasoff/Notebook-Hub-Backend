package com.example.nodebook_hub.notebook_hub_backend.DTO;

public class CheckOutDTO {
    private BookingDTO bookingData;
    private String success_url;
    private String cancel_url;

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public BookingDTO getBookingData() {
        return bookingData;
    }

    public void setBookingData(BookingDTO bookingData) {
        this.bookingData = bookingData;
    }
}
