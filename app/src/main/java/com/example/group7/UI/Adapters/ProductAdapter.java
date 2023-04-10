package com.example.group7.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.Storage.StorageUtils;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
private ArrayList<Product> products;
private Context context;

    public ProductAdapter( Context context) {
        this.products = new ArrayList<Product>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }
    private static class MyView {
        ImageView iv_product_img_home;
        TextView tv_product_name_home;
        TextView tv_product_price_home;
    }
    public void setProducts(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataItem;
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            dataItem = new MyView();
            convertView = inflater.inflate(R.layout.item_product, null);
            dataItem.iv_product_img_home = convertView.findViewById(R.id.iv_product_item_home);
            dataItem.tv_product_name_home = convertView.findViewById(R.id.tv_product_name_home);
            dataItem.tv_product_price_home = convertView.findViewById(R.id.tv_product_price_home);
            convertView.setTag(dataItem);
        }else {
            dataItem = (MyView) convertView.getTag();
        }
        if (products.size() > 0) {
            dataItem.tv_product_name_home.setText(products.get(position).getProduct_name());
            dataItem.tv_product_price_home.setText(products.get(position).getProduct_price() + "");
            StorageUtils.loadStorageImageIntoImageView("product_img", products.get(position).getProduct_img(), dataItem.iv_product_img_home);
        }
        return convertView;
    }
}