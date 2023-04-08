package com.example.group7.models;

public class Category {
    private int id;
    private String img_name;
    private String cate_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String catePhoto_source) {
        this.img_name = catePhoto_source;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cateTitle_photo) {
        this.cate_name = cateTitle_photo;
    }

    public Category() {

    }

    public Category(int id, String cateImg_name, String category_name) {
        this.id = id;
        this.img_name = cateImg_name;
        this.cate_name = category_name;
    }
}
