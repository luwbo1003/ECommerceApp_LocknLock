package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.UI.Storage.Firebase;
import com.example.group7.models.Banner;

import java.util.ArrayList;

public class BannerViewModel extends ViewModel {
    private Firebase firebase;
    private LiveData<ArrayList<Banner>> bannersLiveData;

    public BannerViewModel() {
        firebase = new Firebase();
        bannersLiveData = firebase.getFirebaseData("Banners", Banner.class);
    }

    public LiveData<ArrayList<Banner>> getBannersLiveData() {
        return bannersLiveData;
    }
}
