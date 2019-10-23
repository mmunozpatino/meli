package com.example.meli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel;
import com.example.meli.data.network.responseClasses.ResultProduct
import com.example.meli.data.repository.MeliRepository
import com.example.meli.internal.lazyDeferred
import kotlinx.coroutines.*

class SearchViewModel(
    private val meliRepository: MeliRepository
) : ViewModel() {

    private val searchResult= MutableLiveData<ResultProduct>()

    val _query= MutableLiveData<String>()

    val query : LiveData<String>
        get() = _query

    val result by lazyDeferred{
        meliRepository.searchProduct(_query.value!!)
    }
}
