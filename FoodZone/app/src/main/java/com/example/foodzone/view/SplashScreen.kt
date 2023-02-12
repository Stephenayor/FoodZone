package com.example.foodzone.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.foodzone.AdminActivity
import com.example.foodzone.MainActivity
import com.example.foodzone.R
import com.example.foodzone.UserActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val preferences = getSharedPreferences("login", Context.MODE_PRIVATE)

        Handler().postDelayed({
            if (preferences.contains("adminLogin")) {
                val adminActivityIntent = Intent(this@SplashScreen, AdminActivity::class.java)
                startActivity(adminActivityIntent)
                finish()
            } else if (preferences.contains("userLogin")) {
                val userActivityIntent = Intent(this@SplashScreen, UserActivity::class.java)
                startActivity(userActivityIntent)
                finish()
            } else {
                val startMainActivityIntent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(startMainActivityIntent)
                finish()
            }
        }, 3000)
    }
}