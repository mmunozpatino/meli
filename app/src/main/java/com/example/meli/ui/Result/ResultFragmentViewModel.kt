package com.example.meli.ui.Result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meli.data.network.responseClasses.Result

class ResultFragmentViewModel: ViewModel(){

    val _results = MutableLiveData<ArrayList<Result>>()

    val results: LiveData<ArrayList<Result> >
        get() = _results
}