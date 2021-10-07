/*
 * Copyright 2021 ~ https://github.com/braver-tool ~All rights reserved
 */

package com.section.recyclerview.kotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val launchAppLogoImageView = findViewById<AppCompatImageView?>(R.id.launchAppLogoImageView)
        val launchAppTitleTextView = findViewById<AppCompatTextView?>(R.id.launchAppTitleTextView)
        launchAppLogoImageView.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_from_right)
        launchAppTitleTextView.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_from_left)
        Handler(Looper.getMainLooper()).postDelayed({
            launchAppTitleTextView.visibility = View.GONE
            launchAppTitleTextView.animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_to_right)
            val animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.slide_to_left)
            launchAppLogoImageView.visibility = View.GONE
            launchAppLogoImageView.animation = animation
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                    overridePendingTransition(R.anim.activity_slide_from_right, R.anim.activity_slide_to_left)
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }, 1500)

    }
}