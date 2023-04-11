package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.Storage.StorageUtils;
import com.example.group7.ViewModels.CartViewModel;
import com.example.group7.models.Product;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_add, btn_minus, btn_add_to_cart;
    ImageView iv_arrow_back;
    ImageView iv_product_image;
    TextView tv_price, tv_number,tv_product_name,tv_des ;
    Product product;

    CartViewModel cartViewModel;
    int subtotal, quantityMax, quantity = 1;

    public void assignID(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        iv_product_image = findViewById(R.id.iv_product_image);
        tv_price = findViewById(R.id.tv_price);
        tv_number = findViewById(R.id.tv_number);
        tv_product_name = findViewById(R.id.tv_product_name);

        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        iv_arrow_back.setOnClickListener(this);

        assignID(btn_add, R.id.btn_add);
        assignID(btn_minus, R.id.btn_minus);
        assignID(btn_add_to_cart, R.id.btn_add_to_cart);

        product = (Product) getIntent().getSerializableExtra("product");

        quantityMax = product.getProduct_quantity();
        subtotal = product.getProduct_price();

        tv_product_name.setText(product.getProduct_name());
        tv_number.setText(Integer.toString(quantity));
        tv_price.setText(Integer.toString(subtotal));

        StorageUtils.loadStorageImageIntoImageView("product_img",product.getProduct_img(), iv_product_image);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_arrow_back:
                onBackPressed();
                break;
        }
    }
}