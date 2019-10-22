package com.example.meli.data.network

import androidx.lifecycle.LiveData
import com.example.meli.data.network.responseClasses.Result
import com.example.meli.data.network.responseClasses.ResultProduct

interface ProductResponseDataSource {
    val downloadedSearchResult: LiveData<ResultProduct>

    suspend fun fetchSearchResponse(
        product: String
    )
}