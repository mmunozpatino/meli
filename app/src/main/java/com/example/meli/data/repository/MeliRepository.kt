package com.example.meli.data.repository

import androidx.lifecycle.LiveData
import com.example.meli.data.network.responseClasses.ResultProduct

interface MeliRepository {
    suspend fun searchProduct(): LiveData<ResultProduct>
}