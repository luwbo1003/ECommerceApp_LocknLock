package com.example.group7.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.group7.Storage.Firebase;
import com.example.group7.models.User;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private Firebase firebaseRepository;
    private LiveData<ArrayList<User>> usersLiveData;

    public UserViewModel() {
        firebaseRepository = new Firebase();
        usersLiveData = firebaseRepository.getFirebaseData("Users", User.class);
    }

    public LiveData<ArrayList<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public LiveData<User> getUserByIdFromDb(String userId) {
        return firebaseRepository.getFirebaseSingleData("Users/" + userId, User.class);
    }
}