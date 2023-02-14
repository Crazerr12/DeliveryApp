package com.example.deliveryapp.presentation.categoryscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.data.storage.SharedPrefUserStorage
import com.example.deliveryapp.databinding.FragmentCategoryBinding
import com.example.deliveryapp.presentation.base.BaseFragment
import com.example.deliveryapp.presentation.models.DishModel

class CategoryFragment(
    private val position: Int,
    private val categoryTabs: List<String>,
    private val searchedDishesList: List<DishModel>
) : BaseFragment() {

    override val showBottomNavigationView = true
    lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val sharedPreferences = SharedPrefUserStorage(requireContext())
        val dishesList = sharedPreferences.getDishes()

        val adapter = DishesAdapter()
        binding.recyclerView.adapter = adapter

        if (searchedDishesList.isEmpty()) {
            val dishesListCategory = mutableListOf<DishModel>()
            for (dish in dishesList)
                if (dish.category == categoryTabs[position])
                    dishesListCategory.add(dish)
            adapter.submitList(dishesListCategory)
            dishesListCategory.clear()
        } else {adapter.submitList(searchedDishesList)}

        return binding.root
    }
}