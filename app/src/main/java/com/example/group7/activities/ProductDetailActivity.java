package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView iv_arrow_back;
    ImageView iv_product_image;
    TextView tv_price, tv_number,tv_product_name,tv_des ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        iv_product_image = findViewById(R.id.iv_product_image);
        tv_price = findViewById(R.id.tv_price);
        tv_number = findViewById(R.id.tv_number);
        tv_product_name = findViewById(R.id.tv_product_name);

        Product product = (Product) getIntent().getSerializableExtra("id");
    }
}