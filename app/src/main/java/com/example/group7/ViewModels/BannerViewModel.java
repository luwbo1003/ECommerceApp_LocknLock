package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseDatabase;
import com.example.group7.models.Banner;

import java.util.ArrayList;

public class BannerViewModel extends ViewModel {
    private FirebaseDatabase firebaseDatabase;
    private LiveData<ArrayList<Banner>> bannersLiveData;

    public BannerViewModel() {
        firebaseDatabase = new FirebaseDatabase();
        bannersLiveData = firebaseDatabase.getFirebaseData("Banners", Banner.class);
    }

    public LiveData<ArrayList<Banner>> getBannersLiveData() {
        return bannersLiveData;
    }
}
