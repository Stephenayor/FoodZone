package com.example.foodzone.base

import androidx.fragment.app.Fragment
import com.example.foodzone.di.DaggerFoodZoneComponent
import com.example.foodzone.di.FoodZoneComponent

abstract class BaseFragment : Fragment() {
    protected val foodZoneComponent: FoodZoneComponent
    get() = DaggerFoodZoneComponent.create()
}