package com.ken.infinity.models;

import java.util.List;

public class Artwork {
    private int id;
    private String title;
    private String description;
    private String category;
    private String label;
    private int price;
    private int likes;
    private String imgUrl;
    private int owner_id;
//    private List<Integer> linkToComments;

    public Artwork() {
    }

    public Artwork(int id, String title, String description, String category, String label, int price, int likes, String imgUrl, int owner_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.label = label;
        this.price = price;
        this.likes = likes;
        this.imgUrl = imgUrl;
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }



    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }


//    public List<Integer> getLinkToComments() {
//        return linkToComments;
//    }
//
//    public void setLinkToComments(List<Integer> linkToComments) {
//        this.linkToComments = linkToComments;
//    }
}
