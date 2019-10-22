package com.example.meli.data.network.responseClasses


import com.google.gson.annotations.SerializedName

data class Paging(
    val limit: Int,
    val offset: Int,
    @SerializedName("primary_results")
    val primaryResults: Int,
    val total: Int
)