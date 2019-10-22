package com.example.meli.data.network.responseClasses


data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX>
)