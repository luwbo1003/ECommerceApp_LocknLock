package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.UI.Storage.StorageUtils;
import com.example.group7.ViewModels.CartViewModel;
import com.example.group7.models.Cart;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_add, btn_minus, btn_add_to_cart;
    ImageView iv_arrow_back;
    ImageView iv_product_image;
    TextView tv_price, tv_number,tv_product_name;
    Product product;
    CartViewModel cartViewModel;
    int subtotal, quantityMax, quantity = 1;
    ArrayList<Cart> cartAL;

    public void assignID(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    String userID;
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

       cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
       cartViewModel.getCartsLiveData().observe(this, carts -> {
           if (carts != null){
               cartAL = CartViewModel.getCartsByUserId(carts, userID);
           }
       });

        product = (Product) getIntent().getSerializableExtra("product");
        userID = getIntent().getStringExtra("userID");

        quantityMax = product.getProduct_quantity();
        subtotal = product.getProduct_price();

        tv_product_name.setText(product.getProduct_name());
        tv_number.setText(Integer.toString(quantity));
        tv_price.setText(Integer.toString(subtotal));

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        StorageUtils.loadStorageImageIntoImageView("product_img",product.getProduct_img(), iv_product_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                if (quantity == quantityMax){
                    break;
                }
                quantity += 1;
                tv_number.setText(String.valueOf(quantity));
                break;
            case R.id.btn_minus:
                if (quantity == 1)
                    break;
                quantity -=1;
                tv_number.setText(String.valueOf(quantity));
                break;

            case R.id.iv_arrow_back:
                onBackPressed();
                break;
            case R.id.btn_add_to_cart:
                if (cartAL.size() > 0) {
                    for (Cart cart : cartAL) {
                        if (cart.getProd_id() == product.getId()) {
                            int recentQuantity = quantity + cart.getQuantity();
                            CartViewModel.updateCart(cart.getId(), recentQuantity, this, "Đã thêm vào giỏ hàng", "Thêm sản phẩm thất bại");
                            return;
                        }
                    }
                }
                String key = cartViewModel.getKey() ;
                Cart cart = new Cart(key, userID, product.getId(), quantity);
                CartViewModel.addToCart(cart, key, this, "Đã thêm vào giỏ hàng", "Thêm sản phẩm thất bại");
                break;
        }
    }
}