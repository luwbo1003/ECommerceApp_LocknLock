package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.FirebaseDatabase;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private FirebaseDatabase firebaseDatabase;

//    private ArrayList<Product> products;
    private LiveData<ArrayList<Product>> productLiveData ;

    public ProductViewModel(){
        firebaseDatabase = new FirebaseDatabase();
        productLiveData = firebaseDatabase.getFirebaseData("Products", Product.class);

//        productLiveData = new
    }

    public LiveData<ArrayList<Product>> getProductLiveData() {
        return productLiveData;
    }


}
