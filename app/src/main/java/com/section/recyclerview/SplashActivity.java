/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatImageView launchAppLogoImageView = findViewById(R.id.launchAppLogoImageView);
        AppCompatTextView launchAppTitleTextView = findViewById(R.id.launchAppTitleTextView);
        launchAppLogoImageView.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_from_right));
        launchAppTitleTextView.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_from_left));
        new Handler().postDelayed(() -> {
            launchAppTitleTextView.setVisibility(View.GONE);
            launchAppTitleTextView.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_to_right));
            Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_to_left);
            launchAppLogoImageView.setVisibility(View.GONE);
            launchAppLogoImageView.setAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.activity_slide_from_right, R.anim.activity_slide_to_left);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

        }, 1500);
    }
}