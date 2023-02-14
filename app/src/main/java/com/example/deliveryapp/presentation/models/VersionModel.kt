package com.example.deliveryapp.presentation.models

import com.google.gson.annotations.SerializedName

data class VersionModel(
    @SerializedName("versions")
    val version: List<String>
)