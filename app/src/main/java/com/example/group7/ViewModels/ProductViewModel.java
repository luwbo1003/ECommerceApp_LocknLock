package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.UI.Storage.Firebase;
import com.example.group7.models.Product;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private Firebase firebase;
    private LiveData<ArrayList<Product>> productLiveData ;

    public ProductViewModel(){
        firebase = new Firebase();
        productLiveData = firebase.getFirebaseData("Products", Product.class);
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

    public LiveData<Product> getProductByIdDb(String productID){
        return firebase.getFirebaseSingleData("Products/" + productID, Product.class);
    }
    public LiveData<ArrayList<Product>> searchProducts(String name, int category) {
        MutableLiveData<ArrayList<Product>> filteredData = new MutableLiveData<>();
        productLiveData.observeForever(products -> {
            ArrayList<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                if (category == 200) {
                    if (product.getProduct_name().toLowerCase().contains(name.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                } else {
                    if (product.getProduct_name().toLowerCase().contains(name.toLowerCase()) && product.getProduct_cate() == category) {
                        filteredProducts.add(product);
                    }
                }
            }
            filteredData.setValue(filteredProducts);
        });
        return filteredData;
    }
}
