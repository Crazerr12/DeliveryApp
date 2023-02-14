package com.example.deliveryapp.data.api

import com.example.deliveryapp.presentation.models.*
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/auth/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body body: LoginModel): Response<TokenModel>

    @POST("api/auth/registration")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body body: RegisterModel): Response<TokenModel>

    @GET("api/delivery/dishes/version")
    suspend fun getVersions(
        @Header("Authorization") token: String?
    ): Response<VersionModel>

    @GET("api/delivery/dishes")
    suspend fun getDishes(
        @Header("Authorization") token: String?,
        @Query("version") version: String
    ): Response<List<DishModel>>
}