package com.example.meli.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel;
import com.example.meli.data.network.responseClasses.ResultProduct
import com.example.meli.data.repository.MeliRepository

class SearchViewModel(
    private val meliRepository: MeliRepository
) : ViewModel() {

    val _query= MutableLiveData<String>()

    var q: String = ""


    suspend fun search(): LiveData<ResultProduct> {
        return meliRepository.searchProduct(q)
    }
}
