package com.example.deliveryapp.presentation.onboardingscreens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.FragmentOnBoardingScreenTwoBinding
import com.example.deliveryapp.presentation.base.BaseFragment

class OnBoardingScreenTwoFragment : BaseFragment() {

    override val showBottomNavigationView = false
    lateinit var binding: FragmentOnBoardingScreenTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingScreenTwoBinding.inflate(inflater, container, false)

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        val navigation = findNavController()

        if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        )
            binding.skipAuthorization.isVisible = false

        if (binding.skipAuthorization.isVisible)
            binding.skipAuthorization.setOnClickListener{
                navigation.navigate(R.id.action_onBoardingScreens_to_home_graph)
            }

        binding.buttonSignIn.setOnClickListener {
            navigation.navigate(R.id.action_onBoardingScreens_to_signInScreen)
        }

        binding.buttonSignUp.setOnClickListener {
            navigation.navigate(R.id.action_onBoardingScreens_to_signUpScreen)
        }

        return binding.root
    }
}