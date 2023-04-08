package com.example.group7.Storage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseRepository {
    private DatabaseReference dbRef;

    public FirebaseRepository() {
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public <T> LiveData<ArrayList<T>> getFirebaseData(String nodePath, Class<T> dataType) {
        MutableLiveData<ArrayList<T>> firebaseData = new MutableLiveData<>();
        dbRef.child(nodePath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<T> dataList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    T data = dataSnapshot.getValue(dataType);
                    dataList.add(data);
                }
                firebaseData.setValue(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseData.setValue(null);
            }

        });
        return firebaseData;
    }

//    public void deleteFirebaseData(String nodePath, String key) {
//        dbRef.child(nodePath).child(key).removeValue();
//    }
//
//    public void updateFirebaseData(String nodePath, String key, String detailPath, Object newDetail) {
//        dbRef.child(nodePath).child(key).child(detailPath).setValue(newDetail);
//    }

//    public <T> void addFirebaseData(String nodePath, T data) {
//        String key = dbRef.child(nodePath).push().getKey();
//        // Update the "key" field in the data object
//        if (data instanceof Order) {
//            Order order = (Order) data;
//            order.setId(key);
//        }
//        dbRef.child(nodePath).child(key).setValue(data);
//    }
}
