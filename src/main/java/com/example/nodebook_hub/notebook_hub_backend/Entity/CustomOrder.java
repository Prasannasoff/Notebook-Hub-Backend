package com.example.nodebook_hub.notebook_hub_backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="custom_order_details")
public class CustomOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int note_id;
    private String note_name;
    private String paperType;
    private String size;
    private String ruling;
    private String cover;
    private int quantity;
    private int price;
    private int pages;
    private String city;
    private String street;
    private int zip;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user;

    @Column(name = "ordered_date", nullable = false)
    private LocalDateTime orderedDate;

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;

    @Column(name = "processing_date")
    private LocalDateTime processingDate;

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;

    @Column(name="note-image")
    private byte[] noteImage;

    @Column(name="logo-image")
    private byte[] logoImage;

    public CustomOrder() {
    }

    public CustomOrder(int note_id, String note_name, String paperType, String ruling, String cover, String size, int price, int quantity, int pages, UserDetails user, LocalDateTime orderedDate, LocalDateTime expectedDeliveryDate, LocalDateTime processingDate, LocalDateTime shippedDate, byte[] noteImage, LocalDateTime deliveredDate, byte[] logoImage,String city,String street,int zip) {
        this.note_id = note_id;
        this.note_name = note_name;
        this.paperType = paperType;
        this.ruling = ruling;
        this.cover = cover;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.pages = pages;
        this.user = user;
        this.orderedDate = orderedDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.processingDate = processingDate;
        this.shippedDate = shippedDate;
        this.noteImage = noteImage;
        this.deliveredDate = deliveredDate;
        this.logoImage = logoImage;
        this.street=street;
        this.city=city;
        this.zip=zip;
    }

    public int getNote_id() {
        return note_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRuling() {
        return ruling;
    }

    public void setRuling(String ruling) {
        this.ruling = ruling;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public LocalDateTime getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDateTime orderedDate) {
        this.orderedDate = orderedDate;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public byte[] getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(byte[] noteImage) {
        this.noteImage = noteImage;
    }

    public byte[] getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(byte[] logoImage) {
        this.logoImage = logoImage;
    }
}
