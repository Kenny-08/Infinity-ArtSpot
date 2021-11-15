package com.ken.infinity.models;

public class ExhibitionRegister {

    private int id;
    private String confirm;
    private int user_id;
    private int exhibition_id;

    public ExhibitionRegister() {
    }

    public ExhibitionRegister(int id, String confirm, int user_id, int exhibition_id) {
        this.id = id;
        this.confirm = confirm;
        this.user_id = user_id;
        this.exhibition_id = exhibition_id;
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

    public int getExhibition_id() {
        return exhibition_id;
    }

    public void setExhibition_id(int exhibition_id) {
        this.exhibition_id = exhibition_id;
    }
}

// create table exhibitionRegister(id int not null auto_increment, confirm varchar(225) not null, user_id int not null, exhibition_id int not null, primary key(id), foreign key(user_id) references user(id), foreign key(exhibition_id) references exhibition(id));