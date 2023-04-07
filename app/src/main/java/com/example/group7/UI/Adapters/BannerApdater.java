package com.example.group7.UI.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.group7.R;
import com.example.group7.Storage.StorageUtils;
import com.example.group7.models.Banner;

import java.util.ArrayList;

public class BannerApdater extends RecyclerView.Adapter<BannerViewHolder> {
    private ArrayList<Banner> banners;
    private final LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private Context context;

    public BannerApdater( ViewPager2 viewPager2, Context context) {
        this.banners = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = mInflater.inflate(R.layout.item_advertise, parent,false);
        return new BannerViewHolder(view, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner mBanner = banners.get(position);
        holder.setId(mBanner.getId());
        StorageUtils.loadStorageImageIntoImageView("banner_img", mBanner.getImg_name(),holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public void setBanners(ArrayList<Banner> banners){
        this.banners = banners;
        notifyDataSetChanged();
    }
}