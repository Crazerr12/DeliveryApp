package com.example.deliveryapp.presentation.signinscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.data.api.RetrofitInstance
import com.example.deliveryapp.data.storage.SharedPrefUserStorage
import com.example.deliveryapp.databinding.FragmentSignInScreenBinding
import com.example.deliveryapp.presentation.models.LoginModel
import com.example.deliveryapp.presentation.base.BaseFragment
import com.example.deliveryapp.presentation.common.emailValid
import com.example.deliveryapp.presentation.common.passwordValid
import kotlinx.coroutines.launch

class SignInScreenFragment : BaseFragment() {

    override val showBottomNavigationView = false
    lateinit var binding: FragmentSignInScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInScreenBinding.inflate(inflater, container, false)

        val navigation = findNavController()

        val sharedPreferences = SharedPrefUserStorage(requireContext())

        binding.buttonLogin.setOnClickListener {
            binding.emailInput.error = emailValid(binding.emailEdit.text)
            binding.passwordInput.error = passwordValid(binding.passwordEdit.text)

            if (binding.emailInput.error == null && binding.passwordInput.error == null) {
                val loginInfo = LoginModel(
                    binding.emailEdit.text.toString(),
                    binding.passwordEdit.text.toString()
                )
                    lifecycleScope.launch {
                        val token = RetrofitInstance.retrofit.login(loginInfo).body()
                        sharedPreferences.saveToken(token)
                    }
                navigation.navigate(R.id.action_signInScreen_to_home_graph)
            }
        }
        return binding.root
    }
}