package com.example.deliveryapp.presentation.launchscreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.data.api.RetrofitInstance
import com.example.deliveryapp.data.storage.SharedPrefUserStorage
import com.example.deliveryapp.databinding.FragmentLaunchScreenBinding
import com.example.deliveryapp.presentation.base.BaseFragment
import com.example.deliveryapp.presentation.models.DishModel
import kotlinx.coroutines.launch

class LaunchScreenFragment : BaseFragment() {


    override val showBottomNavigationView = false
    lateinit var binding: FragmentLaunchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchScreenBinding.inflate(inflater, container, false)

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        val navigation = this.findNavController()

        if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        ) {
            val sharedPreferences = SharedPrefUserStorage(requireContext())
            val token = sharedPreferences.getToken()
            var versions = sharedPreferences.getVersions()

            if (token != null) {
                lifecycleScope.launch {
                    if ((RetrofitInstance.retrofit.getVersions("Bearer $token")
                            .body()?.version)?.toSet()?.toList() == versions
                    ) {
                        navigation.navigate(R.id.onBoardingScreens)
                    } else {
                        (RetrofitInstance.retrofit.getVersions("Bearer $token")
                            .body()?.version)?.toSet()?.toList()
                            ?.let {
                                sharedPreferences.saveVersions(it)
                                versions = it
                            }
                        val listDishes = mutableListOf<DishModel>()
                        for (version in versions) {
                            RetrofitInstance.retrofit.getDishes("Bearer $token", version).body()
                                ?.let { listDishes.addAll(it) }
                        }
                        sharedPreferences.saveDishes(listDishes)
                    }
                }
            }

            binding.progressBar.setOnClickListener {
                navigation.navigate(R.id.onBoardingScreens)
            }
        } else binding.progressBar.indeterminateDrawable = null

        return binding.root
    }
}