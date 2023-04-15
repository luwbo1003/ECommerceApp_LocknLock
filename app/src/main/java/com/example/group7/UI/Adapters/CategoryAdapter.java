package com.example.group7.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group7.R;
import com.example.group7.UI.Storage.StorageUtils;
import com.example.group7.UI.ViewHolder.CategoryViewHolder;
import com.example.group7.models.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private ArrayList<Category> categories;
    private final LayoutInflater minflater;
    private Context context;
    private String userID;


    public CategoryAdapter(Context context) {
        this.categories = new ArrayList<Category>();
        this.context = context;
        minflater = LayoutInflater.from(context);
    }
    public void setUserID(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }


    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.item_category, parent,false);
        return new CategoryViewHolder(view, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category mCategory = categories.get(position);
        holder.setUserID(userID);
        holder.setId(mCategory.getId());
        holder.getTv_catename().setText(mCategory.getCate_name());
        StorageUtils.loadStorageImageIntoImageView("category_img", mCategory.getImg_name(),holder.getIv_cateimg());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
