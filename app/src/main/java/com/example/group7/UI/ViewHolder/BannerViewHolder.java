package com.example.group7.UI.ViewHolder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group7.R;
import com.example.group7.UI.Adapters.BannerApdater;
import com.example.group7.models.Banner;
import com.makeramen.roundedimageview.RoundedImageView;

public class BannerViewHolder extends RecyclerView.ViewHolder {
    private BannerApdater bannerApdater;
    private Context mainActivity;
    private int id;
    private RoundedImageView imageView;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoundedImageView getImageView() {
        return imageView;
    }
    public void setImageView(RoundedImageView imageView) {
        this.imageView = imageView;
    }
    public BannerViewHolder(@NonNull View itemView, BannerApdater bannerApdater, Context mainActivity) {
        super(itemView);
        this.bannerApdater = bannerApdater;
        this.mainActivity = mainActivity;
        this.imageView = itemView.findViewById(R.id.imageSlider);
    }
}
