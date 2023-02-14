package com.example.deliveryapp.presentation.categoryscreens

import android.content.Context
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.databinding.ItemDishBinding
import com.example.deliveryapp.presentation.models.DishModel
import com.squareup.picasso.Picasso

class DishViewHolder(private val binding: ItemDishBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: DishModel) = with(binding) {
        Picasso.with(context).load(dish.icon).into(dishIcon)
        dishName.text = dish.name
        dishPrice.text = dish.price
    }
}