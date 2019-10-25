package com.example.meli.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meli.data.network.ProductResponseDataSource
import com.example.meli.data.network.responseClasses.ResultProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MeliRepositoryImpl(
    private val productResponseDataSource: ProductResponseDataSource
) : MeliRepository {

    private val _resultProduct = MutableLiveData<ResultProduct>()

    init{
        productResponseDataSource.downloadedSearchResult.observeForever { newSearchResult ->
            _resultProduct.postValue(newSearchResult)
        }
    }
    override suspend fun searchProduct(product: String): LiveData<ResultProduct> {
        Log.i("mechi" ,"search --> " + product)
        return withContext(Dispatchers.IO){
            initResultData(product)
            return@withContext _resultProduct
        }
    }

    private suspend fun initResultData(product: String){
        fetchProductResult(product)
    }

    private suspend fun fetchProductResult(product: String){
        productResponseDataSource.fetchSearchResponse(product)
    }
}