package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.Firebase;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private Firebase firebase;

//    private ArrayList<Product> products;
    private LiveData<ArrayList<Product>> productLiveData ;

    public ProductViewModel(){
        firebase = new Firebase();
        productLiveData = firebase.getFirebaseData("Products", Product.class);

//        productLiveData = new
    }

    public LiveData<ArrayList<Product>> getProductLiveData() {
        return productLiveData;
    }
    public static Product getProductByIdFromList(ArrayList<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }



}
