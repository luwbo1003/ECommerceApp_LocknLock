package com.example.group7.UI.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group7.R;
import com.example.group7.UI.Adapters.CategoryAdapter;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private CategoryAdapter categoryAdapter;
    private Context mainActivity;
    private int id;
    private TextView tv_catename;
    private ImageView iv_cateimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TextView getTv_catename() {
        return tv_catename;
    }

    public void setTv_catename(TextView tv_catename) {
        this.tv_catename = tv_catename;
    }

    public ImageView getIv_cateimg() {
        return iv_cateimg;
    }

    public void setIv_cateimg(ImageView iv_cateimg) {
        this.iv_cateimg = iv_cateimg;
    }

    public CategoryViewHolder(@NonNull View itemView, CategoryAdapter categoryAdapter, Context mainActivity) {
        super(itemView);

        tv_catename = itemView.findViewById(R.id.tv_CategoryName);
        iv_cateimg = itemView.findViewById(R.id.iv_CategoryImage);

        this.categoryAdapter = categoryAdapter;
        this.mainActivity = mainActivity;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
