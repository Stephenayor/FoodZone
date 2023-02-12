package com.example.foodzone.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodzone.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpViewmodel @Inject constructor () : ViewModel() {
    @Inject
    lateinit var  signUpRepository: SignUpRepository

    fun registerUsers(userEmail : String, userPassword: String) : MutableLiveData<DocumentReference> {
       return signUpRepository.registerUsers(userEmail, userPassword)
    }
}