package com.example.meli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.meli.data.network.responseClasses.ResultProduct
import com.example.meli.data.repository.MeliRepository
import com.example.meli.internal.lazyDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class SearchViewModel(
    private val meliRepository: MeliRepository
) : ViewModel() {

    lateinit var searchResult: LiveData<ResultProduct>

    suspend fun search(product: String): LiveData<ResultProduct>{
        searchResult = meliRepository.searchProduct(product)
        return searchResult
    }
}
