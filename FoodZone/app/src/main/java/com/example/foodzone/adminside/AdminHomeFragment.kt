package com.example.foodzone.adminside

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.foodzone.R
import com.example.foodzone.databinding.FragmentAdminHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AdminHomeFragment : Fragment() {
    private lateinit var binding: FragmentAdminHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminHomeBinding.inflate(layoutInflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        binding.logOutButton.setOnClickListener {
           firebaseAuth.signOut()
        }

        binding.navSwitch.setOnClickListener {
            findNavController(this).navigate(R.id.action_adminHomeFragment_to_userActivity)
        }

        return binding.root
    }

    private fun setupOnBackPressed() {
        requireActivity().onBackPressed()
    }

}

