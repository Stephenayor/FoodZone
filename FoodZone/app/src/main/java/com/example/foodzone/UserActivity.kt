package com.example.foodzone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodzone.databinding.ActivityAdminBinding
import com.example.foodzone.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}