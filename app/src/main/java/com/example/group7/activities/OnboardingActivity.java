package com.example.group7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.UI.Fragment.HomeFragment;
import com.example.group7.adapter.SliderAdapter;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;
    TextView[] dots_text;
    Button letsGetStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        //add Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view) {
        startActivity(new Intent(this, HomeFragment.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {
        dots_text = new TextView[3];
        dots.removeAllViews();

        for (int i = 0; i < dots_text.length; i++) {
            dots_text[i] = new TextView(this);
            dots_text[i].setText(Html.fromHtml("&#8226;"));
            dots_text[i].setTextSize(35);

            dots.addView(dots_text[i]);
        }
        if (dots_text.length > 0) {
            dots_text[position].setTextColor(getResources().getColor(R.color.dot_light_screen1));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.side);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}