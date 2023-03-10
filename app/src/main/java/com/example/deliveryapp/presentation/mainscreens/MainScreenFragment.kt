package com.example.deliveryapp.presentation.mainscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.deliveryapp.R
import com.example.deliveryapp.data.storage.SharedPrefUserStorage
import com.example.deliveryapp.databinding.FragmentMainScreenBinding
import com.example.deliveryapp.presentation.base.BaseFragment
import com.example.deliveryapp.presentation.models.DishModel
import com.google.android.material.tabs.TabLayoutMediator


class MainScreenFragment : BaseFragment() {

    override val showBottomNavigationView = true
    lateinit var binding: FragmentMainScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val sharedPreferences = SharedPrefUserStorage(requireContext())

        val dishesList = sharedPreferences.getDishes()
        val categoryList = mutableListOf<String>()
        val searchedList = mutableListOf<DishModel>()
        var adapter: PagerAdapter

        for (dish in dishesList) {
            if (dish.category !in categoryList)
                categoryList.add(dish.category)
        }
        adapter = PagerAdapter(this@MainScreenFragment, categoryList, emptyList())

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = categoryList[position]
        }.attach()

        binding.imageSearch.setOnClickListener{
            binding.addressInput.isVisible = false
            binding.searchView.isIconified = false
            binding.searchView.isVisible = true
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchedList.clear()
                binding.tabLayout.isVisible = false
                binding.result.isVisible = true
                for (dish in dishesList) {
                    if (query?.let { dish.name.contains(it) } == true) {
                        searchedList.add(dish)
                    }
                }
                adapter = PagerAdapter(this@MainScreenFragment, emptyList(), searchedList)
                binding.viewPager2.adapter = adapter
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchedList.clear()
                binding.tabLayout.isVisible = false
                binding.result.isVisible = true
                for (dish in dishesList) {
                    if (dish.name.contains(binding.searchView.query)) {
                        searchedList.add(dish)
                    }
                }
                adapter = PagerAdapter(this@MainScreenFragment, emptyList(), searchedList)
                binding.viewPager2.adapter = adapter
                return false
            }
        })

        binding.searchView.setOnCloseListener {

            binding.searchView.isVisible = false
            binding.addressInput.isVisible = true
            binding.tabLayout.isVisible = true
            binding.result.isVisible = false

            for (dish in dishesList) {
                if (dish.category !in categoryList)
                    categoryList.add(dish.category)
            }
            adapter = PagerAdapter(this@MainScreenFragment, categoryList, emptyList())

            binding.viewPager2.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                tab.text = categoryList[position]
            }.attach()

            false
        }

        return binding.root
    }
}