package com.example.meli.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meli.data.repository.MeliRepository

class SearchProductViewModelFactory(
    private val meliRepository: MeliRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(meliRepository) as T
    }
}