package com.example.foodzone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.foodzone.base.BaseFragment
import com.example.foodzone.databinding.FragmentSignUpBinding
import com.example.foodzone.viewmodel.SignUpViewmodel
import com.example.foodzone.viewmodel.SignUpViewmodelFactory
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject


class SignUpFragment : BaseFragment() {
    private lateinit var binding: FragmentSignUpBinding
    var isDetailsValid = true

    @Inject
    lateinit var signUpViewmodel: SignUpViewmodel
    private lateinit var documentReference: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.signUpBtnSignUpPage.setOnClickListener {
            registerUsers()
        }
        // navigate to login Fragment
        binding.signInTvSignUpPage.setOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpViewmodel = foodZoneComponent.getSignUpViewModel()
    }

    private fun setupViewmodel() {
//        val application = requireNotNull(this.activity).application
//        val viewModelFactory = SignUpViewmodelFactory(application)
//        signUpViewmodel =
//            ViewModelProvider(
//                this, viewModelFactory
//            ).get(SignUpViewmodel::class.java)
    }

    private fun registerUsers() = with(binding) {
        checkField(fullNameSignUpPage)
        checkField(signUpEmail)
        checkField(signUpPassword)

        if (isDetailsValid) {
            val userEmail = binding.signUpEmail.text.toString()
            val userPassword = binding.signUpPassword.text.toString()
            activity?.let {
                signUpViewmodel.registerUsers(userEmail, userPassword).observe(it, Observer {
                    documentReference = it
                })
            }
            val userSignupDetails: MutableMap<String, Any> = java.util.HashMap<String, Any>()
            userSignupDetails["Full name"] = binding.fullNameSignUpPage.text.toString()
            userSignupDetails["User Email"] = binding.signUpEmail.text.toString()
            userSignupDetails["Password"] = binding.signUpPassword.text.toString()
            //Specify if the user is Admin
            if (binding.checkboxAdmin.isChecked) {
                userSignupDetails["isAdmin"] = 1
            }
            //Specify if the user is not Admin
            if (binding.checkboxUser.isChecked) {
                userSignupDetails["isUser"] = 1
            }
            documentReference.set(userSignupDetails)
            if (binding.checkboxAdmin.isChecked) {
                Toast.makeText(
                    context, "YOU SIGNED UP AS AN ADMIN",
                    Toast.LENGTH_SHORT
                ).show()
                view?.let { it1 ->
                    Navigation.findNavController(it1)
                        .navigate(R.id.action_signUpFragment_to_adminActivity)
                }
                activity?.finish()
            }
            if (binding.checkboxUser.isChecked) {
                view?.let { it1 ->
                    Navigation.findNavController(it1)
                        .navigate(R.id.action_signUpFragment_to_userActivity)
                }
                activity?.finish()
            }

        }
    }

    private fun checkField(textField: EditText): Boolean {
        if (textField.text.toString().isEmpty()) {
            textField.error = "Error"
            isDetailsValid = false
        } else {
            isDetailsValid = true
        }
        return isDetailsValid
    }
}


