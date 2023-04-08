package com.example.group7.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseRepository;
import com.example.group7.models.Category;

import java.io.Closeable;
import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Category>> categoriesLiveData;


    public CategoryViewModel() {
        firebaseRepository = new FirebaseRepository();
        categoriesLiveData = firebaseRepository.getFirebaseData("Categories", Category.class);
    }

    public LiveData<ArrayList<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}
