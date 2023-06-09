package com.example.group7.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.group7.R;
import com.example.group7.ViewModels.UserViewModel;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 4000;
    ImageView action_image;
    Animation move;
    SharedPreferences onBoardingScreen, loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        action_image = findViewById(R.id.action_image);
        move = AnimationUtils.loadAnimation(this, R.anim.move);
        action_image.setAnimation(move);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
//                boolean isFirstLaunch = onBoardingScreen.getBoolean("firstTime", true);

//                if (isFirstLaunch) {
//                    SharedPreferences.Editor editor = onBoardingScreen.edit();
//                    editor.putBoolean("firstTime", false);
//                    editor.commit();
//
//
//                } //else {
////                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
////                    startActivity(intent);
////                    finish();
////                }


                loginPreferences = getSharedPreferences("isLoggin", MODE_PRIVATE);
                Boolean isLogged = loginPreferences.getBoolean("isLogged", false);
                String userId = loginPreferences.getString("userId", "");

                if (isLogged) {
                    userViewModel.getUserByIdFromDb(userId).observe(WelcomeActivity.this, user -> {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra("id", user.getId());
                        intent.putExtra("email", user.getEmail());
                        startActivity(intent);
                        return;
                    });

                } else {
                    Intent intent = new Intent(getBaseContext(), OnboardingActivity.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_TIMER);

    }
}
