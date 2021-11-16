package com.ken.infinity.models;

public class WorkshopRegister {
    private int id;
    private String confirm;
    private int user_id;
    private int workshop_id;

    public WorkshopRegister() {
    }

    public WorkshopRegister(int id, String confirm, int user_id, int workshop_id) {
        this.id = id;
        this.confirm = confirm;
        this.user_id = user_id;
        this.workshop_id = workshop_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(int workshop_id) {
        this.workshop_id = workshop_id;
    }
}


