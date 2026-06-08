package com.example.nodebook_hub.notebook_hub_backend.Entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name="user_data")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name="name",length = 50)
    private String name;
    @Column(name="email", length=100, unique=true)
    private String email;

    @Column(name="password", length=100)
    private String password;

    @Column(name="address",length=200)
    private String address;

    @Column(name="phone_number", length=15)
    private String phone_number;

    @Column(name="image")
    private byte[] image;

    @Column(name = "admin")
    private Boolean admin = false;

    @PrePersist
    protected void prePersist() {
        if (admin == null) {
            admin = false;
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +

                ", admin=" + admin +
                '}';
    }
}
