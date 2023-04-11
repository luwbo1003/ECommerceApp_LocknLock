package com.example.group7.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private  String product_name;
    private String product_img;
    private int product_price;
    private int product_quantity;
    private  int product_cate;

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

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getProduct_cate() {
        return product_cate;
    }

    public void setProduct_cate(int product_cate) {
        this.product_cate = product_cate;
    }

    public Product(){

    }
    public Product(int id, String product_name, String product_img, int product_price, int product_quantity, int product_cate) {
        this.id = id;
        this.product_name = product_name;
        this.product_img = product_img;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_cate = product_cate;
    }
}