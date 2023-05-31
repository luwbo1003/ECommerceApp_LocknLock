package com.example.group7.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.UI.Storage.Firebase;
import com.example.group7.models.Order;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {
    private static Firebase firebase;
    private LiveData<ArrayList<Order>> ordersLiveData;

    public OrderViewModel() {
        firebase = new Firebase();
        ordersLiveData = firebase.getFirebaseData("Orders", Order.class);
    }
    public LiveData<ArrayList<Order>> getOrdersLiveData() {
        return ordersLiveData;
    }

    public void addOrder(Order data, String key, Context context, String success, String fail) {
        firebase.addFirebaseData("Orders", data, key, context, success, fail);
    }
    public String getOrderKey() {
        return firebase.getKey("Orders");
    }
    public static ArrayList<Order> getOrdersByUserId(ArrayList<Order> orders, String userId) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser_id().equals(userId)) {
                orderArrayList.add(order);
            }
        }
        return orderArrayList;
    }
}