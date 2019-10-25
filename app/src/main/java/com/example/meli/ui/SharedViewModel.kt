package com.example.meli.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meli.data.network.responseClasses.Result

class SharedViewModel: ViewModel() {

    val _results = MutableLiveData<ArrayList<Result>>()

    val results: LiveData<ArrayList<Result>>
        get() = _results

    val _selected = MutableLiveData<Result>()

    val selected: LiveData<Result>
        get() = _selected

    val query = MutableLiveData<String>()

}