package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseDatabase;
import com.example.group7.models.Category;

import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private FirebaseDatabase firebaseDatabase;
    private LiveData<ArrayList<Category>> categoriesLiveData;


    public CategoryViewModel() {
        firebaseDatabase = new FirebaseDatabase();
        categoriesLiveData = firebaseDatabase.getFirebaseData("Categories", Category.class);
    }

    public LiveData<ArrayList<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}
