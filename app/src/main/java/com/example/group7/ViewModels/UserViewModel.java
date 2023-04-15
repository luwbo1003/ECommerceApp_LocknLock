package com.example.group7.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.UI.Storage.Firebase;
import com.example.group7.models.User;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private static Firebase firebase;
    private LiveData<ArrayList<User>> usersLiveData;

    public UserViewModel() {
        firebase = new Firebase();
        usersLiveData = firebase.getFirebaseData("Users", User.class);
    }
    public static void updatePassword(String id, String detail, Context context, String success, String fail){
        firebase.updateFirebaseData("Users", id, "password", detail,context, success, fail);
    }

    public LiveData<ArrayList<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public LiveData<User> getUserByIdFromDb(String userId) {
        return firebase.getFirebaseSingleData("Users/" + userId, User.class);
    }
}