package com.example.deliveryapp.presentation.categoryscreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.databinding.ItemDishBinding
import com.example.deliveryapp.presentation.models.DishModel

class DishesAdapter : RecyclerView.Adapter<DishViewHolder>() {

    private val items = mutableListOf<DishModel>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
            ItemDishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        , parent.context)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(dishes: List<DishModel>){
        items.clear()
        items.addAll(dishes)
        notifyDataSetChanged()
    }
}