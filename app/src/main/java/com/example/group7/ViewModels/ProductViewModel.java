package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseRepository;
import com.example.group7.models.Category;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Product>> productLiveData ;

    public ProductViewModel(){
        firebaseRepository = new FirebaseRepository();
        productLiveData = firebaseRepository.getFirebaseData("Products", Product.class);
    }

    public LiveData<ArrayList<Product>> getProductLiveData() {
        return productLiveData;
    }
}
