package com.example.deliveryapp.presentation.signupscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.data.api.RetrofitInstance
import com.example.deliveryapp.data.storage.SharedPrefUserStorage
import com.example.deliveryapp.databinding.FragmentSignUpScreenBinding
import com.example.deliveryapp.presentation.models.RegisterModel
import com.example.deliveryapp.presentation.base.BaseFragment
import com.example.deliveryapp.presentation.common.emailValid
import com.example.deliveryapp.presentation.common.passwordValid
import kotlinx.coroutines.launch

class SignUpScreenFragment : BaseFragment() {

    override val showBottomNavigationView = false
    lateinit var binding: FragmentSignUpScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpScreenBinding.inflate(inflater, container, false)

        val navigation = findNavController()

        val sharedPreferences = SharedPrefUserStorage(requireContext())

        binding.buttonRegister.setOnClickListener{
            binding.emailInput.error = emailValid(binding.emailEdit.text)
            binding.passwordInput.error = passwordValid(binding.passwordEdit.text)

            if ( binding.emailInput.error == null && binding.passwordInput.error == null) {

                val registerInfo = RegisterModel(
                    name = binding.nameEdit.text.toString(),
                    email = binding.emailEdit.text.toString(),
                    password = binding.passwordEdit.text.toString()
                )
                    lifecycleScope.launch {
                    val token = RetrofitInstance.retrofit.register(registerInfo).body()
                    sharedPreferences.saveToken(token)
                }

                navigation.navigate(R.id.action_signUpScreen_to_home_graph)
            }
        }

        binding.buttonCancel.setOnClickListener{
            navigation.navigate(R.id.action_signUpScreen_to_signInScreen)
        }

        return binding.root
    }
}