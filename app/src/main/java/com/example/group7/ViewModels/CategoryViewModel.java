package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.UI.Storage.Firebase;
import com.example.group7.models.Category;

import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private Firebase firebase;
    private LiveData<ArrayList<Category>> categoriesLiveData;


    public CategoryViewModel() {
        firebase = new Firebase();
        categoriesLiveData = firebase.getFirebaseData("Categories", Category.class);
    }

    public LiveData<ArrayList<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}
