package com.example.meli.data.network.responseClasses


import com.google.gson.annotations.SerializedName

data class Value(
    val id: String,
    val name: String,
    val results: Int
)