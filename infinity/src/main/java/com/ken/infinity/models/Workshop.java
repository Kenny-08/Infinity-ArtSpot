package com.ken.infinity.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Workshop {
    private int id;
    private String title;
    private String description;
    private String mode;
    private int organizer_id;
    private Timestamp datetime;
    private String venue;
    private int total_seats;
    private int registered_seats;
    private String imgUrl;
    private String status;

    public Workshop() {
    }


    public Workshop(int id, String title, String description, String mode, int organizer_id, Timestamp datetime, String venue, int total_seats, int registered_seats, String imgUrl, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mode = mode;
        this.organizer_id = organizer_id;
        this.datetime = datetime;
        this.venue = venue;
        this.total_seats = total_seats;
        this.registered_seats = registered_seats;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(int organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getRegistered_seats() {
        return registered_seats;
    }

    public void setRegistered_seats(int registered_seats) {
        this.registered_seats = registered_seats;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


//    create table workshop(id int not null auto_increment, title varchar(1000) not null, description varchar(5000) not null, mode varchar(225) not null, organizer_id int not null, datetime datetime not null, venue varchar(2000) not null, total_seats int not null, registered_seats int, imgUrl varchar(1000) not null, status varchar(225) not null, primary key (id), foreign key(organizer_id) references user(id));