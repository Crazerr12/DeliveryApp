package com.example.deliveryapp.presentation.mainscreens

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deliveryapp.presentation.categoryscreens.CategoryFragment
import com.example.deliveryapp.presentation.models.DishModel

class PagerAdapter(
    fragment: Fragment,
    private var categoryTabs: List<String>,
    private var searchedList: List<DishModel>
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return if (searchedList.isEmpty())
            categoryTabs.size
        else 1
    }

    override fun createFragment(position: Int): Fragment {
        return if (searchedList.isEmpty())
            CategoryFragment(position, categoryTabs, emptyList())
        else CategoryFragment(position, emptyList(), searchedList)
    }
}