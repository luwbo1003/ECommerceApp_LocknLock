package com.example.group7.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.Firebase;
import com.example.group7.models.Cart;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {
    private static Firebase firebase;
    private LiveData<ArrayList<Cart>> cartsLiveData;

    public CartViewModel() {
        firebase = new Firebase();
        cartsLiveData = firebase.getFirebaseData("Carts", Cart.class);
    }

    public LiveData<ArrayList<Cart>> getCartsLiveData() {
        return cartsLiveData;
    }

    public static ArrayList<Cart> getCartsByUserId(ArrayList<Cart> cartList, String userId) {
        ArrayList<Cart> cartArrayList = new ArrayList<>();
        int i = 0;
        while (i < cartList.size()) {
            Cart cart = cartList.get(i);
            if (cart.getUser_id() != null && cart.getUser_id().equals(userId)) {
                cartArrayList.add(cart);
            }
            i++;
        }
        return cartArrayList;
    }

    public static void deleteCart(String id){
        firebase.deleteFirebaseData("Carts", id);
    }

    public static void updateCart(String id, int detail){
        firebase.updateFirebaseData("Carts", id, "quantity", detail );
    }

    public static void  updateCart(String id, int detail, Context context, String success, String fail ){
        firebase.updateFirebaseData("Carts", id, "quantity", detail , context ,success, fail);
    }

    public static void addToCart(Cart data, String key, Context context, String success, String fail){
        firebase.addFirebaseData("Carts", data, key, context, success, fail);
    }

    public String getKey(){
        return firebase.getKey("Carts");
    }
}