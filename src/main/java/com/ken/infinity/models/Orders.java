package com.ken.infinity.models;

import java.sql.Timestamp;

public class Orders {

    private int id;
    private String email;
    private int price;
    private String address;
    private String status;
    private Timestamp ordered_at;
    private int user_id;
    private int artwork_id;

    public Orders() {
    }

    public Orders(int id, String email, int price, String address, String status, Timestamp ordered_at, int user_id, int artwork_id) {
        this.id = id;
        this.email = email;
        this.price = price;
        this.address = address;
        this.status = status;
        this.ordered_at = ordered_at;
        this.user_id = user_id;
        this.artwork_id = artwork_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrdered_at() {
        return ordered_at;
    }

    public void setOrdered_at(Timestamp ordered_at) {
        this.ordered_at = ordered_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArtwork_id() {
        return artwork_id;
    }

    public void setArtwork_id(int artwork_id) {
        this.artwork_id = artwork_id;
    }
}

