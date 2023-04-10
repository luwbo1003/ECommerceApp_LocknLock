package com.example.group7.models;

public class Product {
    private int id;
    private  String product_name;
    private String product_img;
    private String product_price;
    private String product_des;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_des() {
        return product_des;
    }

    public void setProduct_des(String product_des) {
        this.product_des = product_des;
    }

    public Product() {

    }

    public Product(int id, String product_name, String proImg_name, String product_price, String product_des) {
        this.id = id;
        this.product_name = product_name;
        this.product_img = proImg_name;
        this.product_price = product_price;
        this.product_des = product_des;
    }
}
