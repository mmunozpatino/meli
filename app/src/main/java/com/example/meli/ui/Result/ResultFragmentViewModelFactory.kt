package com.example.meli.ui.Result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultFragmentViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultFragmentViewModel() as T
    }
}