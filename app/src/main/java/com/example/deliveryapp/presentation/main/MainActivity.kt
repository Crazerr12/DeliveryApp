package com.example.deliveryapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ActivityMainBinding
import com.example.deliveryapp.presentation.managers.BottomnavigationViewManager

class MainActivity : AppCompatActivity(), BottomnavigationViewManager {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun setNavigationViewVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }
}