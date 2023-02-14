package com.example.deliveryapp.presentation.onboardingscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.databinding.FragmentOnBoardingScreensBinding
import com.example.deliveryapp.presentation.base.BaseFragment

class OnBoardingScreens : BaseFragment() {

    override val showBottomNavigationView = false
    lateinit var binding: FragmentOnBoardingScreensBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingScreensBinding.inflate(inflater, container, false)

        val fragList = listOf<Fragment>(
            OnBoardingScreenOneFragment(),
            OnBoardingScreenTwoFragment(),
        )
        binding.viewPager.adapter = ViewPagerAdapter(this, fragList)
        return binding.root
    }
}