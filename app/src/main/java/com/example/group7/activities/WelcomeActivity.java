package com.example.group7.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.group7.UI.Fragment.HomeFragment;
import com.example.group7.activities.OnboardingActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group7.R;

public class WelcomeActivity extends AppCompatActivity {

    private static  int SPLASH_TIMER = 5000;
    ImageView action_image;
    Animation move;
    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        action_image = findViewById(R.id.action_image);
        move = AnimationUtils.loadAnimation(this, R.anim.move);
        action_image.setAnimation(move);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstLaunch = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstLaunch){
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIMER);
    }
}
