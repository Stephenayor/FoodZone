package com.example.foodzone.di

import com.example.foodzone.viewmodel.SignUpViewmodel
import dagger.Component

@Component
interface FoodZoneComponent {
    fun getSignUpViewModel(): SignUpViewmodel
}