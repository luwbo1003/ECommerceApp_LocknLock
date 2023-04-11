package com.example.group7.models;

public class Product {
    private int id;
    private  String product_name;
    private String product_img;
    private int product_price;
    private int product_quantity;

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

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_des() {
        return product_quantity;
    }

    public void setProduct_des(int product_des) {
        this.product_quantity = product_des;
    }

    public Product() {

    }

    public Product(int id, String product_name, String proImg_name, int product_price, int product_quantity) {
        this.id = id;
        this.product_name = product_name;
        this.product_img = proImg_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }
}