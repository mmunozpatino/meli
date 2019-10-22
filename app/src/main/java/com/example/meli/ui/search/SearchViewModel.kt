package com.example.meli.ui.search

import androidx.lifecycle.ViewModel;
import com.example.meli.data.repository.MeliRepository
import com.example.meli.internal.lazyDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class SearchViewModel(
    private val meliRepository: MeliRepository
) : ViewModel() {
    val result by lazyDeferred{
        meliRepository.searchProduct()
    }
}
