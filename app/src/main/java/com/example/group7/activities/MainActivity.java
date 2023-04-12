package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.group7.R;
import com.example.group7.UI.Fragment.OrderFragment;
import com.example.group7.databinding.ActivityMainBinding;
import com.example.group7.UI.Fragment.CartFragment;
import com.example.group7.UI.Fragment.HomeFragment;
import com.example.group7.UI.Fragment.UserFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.order:
                    replaceFragment(new OrderFragment());
                    break;
                case R.id.user:
                    replaceFragment(new UserFragment());
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        Bundle args = new Bundle();
        args.putString("id", getIntent().getStringExtra("id"));
        args.putString("email", getIntent().getStringExtra("email"));
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}