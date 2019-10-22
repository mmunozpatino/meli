package com.example.meli.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meli.data.network.ProductResponseDataSource
import com.example.meli.data.network.responseClasses.ResultProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchProductRepositoryImpl(
    private val productResponseDataSource: ProductResponseDataSource
) : SearchProductRepository {

    private val _resultProduct = MutableLiveData<ResultProduct>()

    init{
        productResponseDataSource.downloadedSearchResult.observeForever { newSearchResult ->
            _resultProduct.postValue(newSearchResult)
        }
    }
    override suspend fun searchProduct(): LiveData<ResultProduct> {
        return withContext(Dispatchers.IO){
            initResultData()
            return@withContext _resultProduct
        }
    }

    private suspend fun initResultData(){
        fetchProductResult()
    }

    private suspend fun fetchProductResult(){
        productResponseDataSource.fetchSearchResponse("moto g6")
    }
}