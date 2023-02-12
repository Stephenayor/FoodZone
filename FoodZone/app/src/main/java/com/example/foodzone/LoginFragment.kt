package com.example.foodzone

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.foodzone.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    var isDetailsValid = true
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        binding.loginBtn.setOnClickListener {
            logUserIn()
        }
        binding.signUpButtonInLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        return binding.root
    }

    private fun logUserIn() = with(binding) {
        checkTextField(loginEmailEditText)
        checkTextField(signInPassword)
        if (isDetailsValid) {
            val signInEmail = binding.loginEmailEditText.text.toString()
            val signInPassword = binding.signInPassword.text.toString()

            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnSuccessListener {
                    val sharedPreferences =
                        activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
//                    val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
                    editor = sharedPreferences?.edit() ?:
                    editor.putString("User name", it.user?.email)
                    editor.apply()
                    it.user?.uid?.let { it1 -> checkUserAccessLevel(it1) }
                    Toast.makeText(
                        activity, "SUCCESSFUL LOGIN",
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }

        }
    }

    private fun checkUserAccessLevel(uid: String) {
        val documentReference: DocumentReference = firestore.collection("Users")
            .document(uid)
        documentReference.get().addOnSuccessListener {
            if (it.get("isAdmin") != null) {
                Toast.makeText(activity, "ADMIN IN SUCCESSFULLY", Toast.LENGTH_LONG).show()
                editor.putBoolean("adminLogin", true)
                editor.apply()
                view?.let { it1 ->
                    findNavController(it1)
                        .navigate(R.id.action_loginFragment_to_adminActivity)
                }
            }

            if (it.getString("isUser") != null) {
                editor.putBoolean("userLogin", true)
                editor.apply()
                Toast.makeText(activity, "USER IN SUCCESSFULLY", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkTextField(textField: EditText): Boolean {
        if (textField.text.toString().isEmpty()) {
            textField.error = "Error"
            isDetailsValid = false
        } else {
            isDetailsValid = true
        }
        return isDetailsValid
    }

//    override fun onStart() {
//        super.onStart()
//        if (firebaseAuth.currentUser != null) {
//            val documentReference: DocumentReference = firestore.collection("Users")
//                .document(firebaseAuth.currentUser!!.uid)
//            documentReference.get().addOnSuccessListener {
//                if (it.get("isAdmin") != null) {
//                    view?.let { it1 ->
//                        Navigation.findNavController(it1)
//                            .navigate(R.id.action_loginFragment_to_adminActivity)
//                    }
//                    activity?.finish()
//                }
//
//                if (it.getString("isUser") != null) {
//                    Toast.makeText(activity, "USER SUCCESSFUL", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }


}