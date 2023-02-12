package com.example.foodzone.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpRepository @Inject constructor() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    fun registerUsers(userEmail : String, userPassword: String) : MutableLiveData<DocumentReference> {
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        val mutableLiveData: MutableLiveData<DocumentReference> = MutableLiveData<DocumentReference>()
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(

            ) { task ->
                if (task.isSuccessful) {
                    val registeredUser = firebaseAuth.currentUser
                    var documentReference: DocumentReference =
                        firestore.collection("Users")
                            .document(
                                registeredUser?.uid ?: "No user Available"
                            )
                    mutableLiveData.value = documentReference
                } else {
                    Log.d("Authentication", "createUserWithEmail:failure", task.exception)

                }
            }
        return mutableLiveData
    }

}