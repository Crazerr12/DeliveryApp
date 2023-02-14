package com.example.deliveryapp.data.storage

import com.example.deliveryapp.presentation.models.DishModel
import com.example.deliveryapp.presentation.models.TokenModel
import com.example.deliveryapp.presentation.models.VersionModel
import retrofit2.Response

interface UserStorage {

    fun saveToken(token: TokenModel?)

    fun getToken(): String?

    fun saveVersions(versions: List<String>)

    fun getVersions(): List<String>

    fun saveDishes(dishes: List<DishModel>)

    fun getDishes(): List<DishModel>
}