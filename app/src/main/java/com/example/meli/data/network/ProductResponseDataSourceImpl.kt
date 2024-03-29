package com.example.meli.data.network

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meli.data.network.responseClasses.Result
import com.example.meli.data.network.responseClasses.ResultProduct
import com.example.meli.internal.NoConnectivityExeption
import kotlinx.coroutines.channels.consumesAll
import kotlin.coroutines.coroutineContext

class ProductResponseDataSourceImpl(
    private val meliApiService: MeliApiService
) : ProductResponseDataSource {

    private val _downloadedSearchResult = MutableLiveData<ResultProduct>()

    override val downloadedSearchResult: LiveData<ResultProduct>
        get() = _downloadedSearchResult

    override suspend fun fetchSearchResponse(product: String) {
        try{
            val fetchedSearchResult= meliApiService
                .searchProduct(product)
                .await()

            _downloadedSearchResult.postValue(fetchedSearchResult)

        }catch(e: NoConnectivityExeption){
            Log.e("connectivity", "No internet connection", e)

        }
    }
}