package com.example.foodzone.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodzone.viewmodel.SignUpViewmodel
import com.example.foodzone.viewmodel.SignUpViewmodelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class SignUpViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: SignUpViewmodelFactory
    ): ViewModelProvider.Factory

    @Binds
    abstract fun bindContactSourcesViewModel(viewModel: SignUpViewmodel): ViewModel
}