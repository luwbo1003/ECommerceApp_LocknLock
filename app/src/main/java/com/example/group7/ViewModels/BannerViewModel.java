package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseRepository;
import com.example.group7.models.Banner;

import java.util.ArrayList;

public class BannerViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Banner>> bannersLiveData;

    public BannerViewModel() {
        firebaseRepository = new FirebaseRepository();
        bannersLiveData = firebaseRepository.getFirebaseData("Banners", Banner.class);
    }

    public LiveData<ArrayList<Banner>> getBannersLiveData() {
        return bannersLiveData;
    }
}
