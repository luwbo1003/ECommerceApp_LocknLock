package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.group7.R;
import com.example.group7.UI.Adapters.ProductAdapter;
import com.example.group7.ViewModels.ProductViewModel;

public class SearchActivity extends AppCompatActivity {
    ProductViewModel productViewModel;
    int category_id;
    ProductAdapter productAdapter;

    ImageView iv_arrow_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        iv_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        category_id = getIntent().getIntExtra("cate_id",200);

        String productName = getIntent().getStringExtra("product_name");
        //gridview
        GridView gridView = findViewById(R.id.gridview_product_search);
        productAdapter = new ProductAdapter(getBaseContext());
        gridView.setAdapter(productAdapter);

        //Click

        SearchView searchView = findViewById(R.id.sv_product_search);
        searchView.clearFocus();
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String product_name) {
                productViewModel.searchProducts(product_name, category_id).observe(SearchActivity.this, products -> {
                    if (products!= null){
                        productAdapter.setProducts(products);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productViewModel.searchProducts(newText, category_id).observe(SearchActivity.this, products -> {
                    if (products!= null){
                        productAdapter.setProducts(products);
                    }
                });
                return true;
            }

        });
        searchView.setQuery(productName, false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pro_id = String.valueOf(gridView.getItemIdAtPosition(position));
                productViewModel.getProductByIdDb(pro_id).observe(SearchActivity.this, product -> {
                    Intent intent = new Intent(SearchActivity.this, ProductDetailActivity.class);
                    intent.putExtra("product", product);
                    intent.putExtra("userID", getIntent().getStringExtra("userID"));
                    startActivity(intent);
                });
            }
        });
    }
}