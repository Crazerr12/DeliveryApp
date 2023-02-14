package com.example.deliveryapp.data.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.deliveryapp.presentation.models.DishModel
import com.example.deliveryapp.presentation.models.TokenModel
import com.example.deliveryapp.presentation.models.VersionModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import kotlin.collections.ArrayList

private const val SHARED_PREF_NAME = "SHARED_PREF"

class SharedPrefUserStorage(context: Context) : UserStorage {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    override fun saveToken(token: TokenModel?) {
        sharedPreferences.edit().putString("TOKEN", token?.token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    override fun saveVersions(versions: List<String>) {
        sharedPreferences.edit().putString("VERSIONS", Gson().toJson(versions)).apply()
    }

    override fun getVersions(): List<String> {
        return if (sharedPreferences.getString("VERSIONS", null) == null) emptyList() else {
            Gson().fromJson(
                sharedPreferences.getString("VERSIONS", null),
                object : TypeToken<List<String>>() {}.type
            )
        }
    }

    override fun saveDishes(dishes: List<DishModel>) {
        sharedPreferences.edit().putString("DISHES", Gson().toJson(dishes)).apply()
    }

    override fun getDishes(): List<DishModel> {
        return if (sharedPreferences.getString("DISHES", null) == null) {
            emptyList()
        } else Gson().fromJson(
            sharedPreferences.getString("DISHES", null),
            object : TypeToken<List<DishModel>>() {}.type
        )
    }
}