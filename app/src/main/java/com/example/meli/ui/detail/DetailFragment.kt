package com.example.meli.ui.detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.meli.R
import com.example.meli.ui.SharedViewModel
import com.example.meli.ui.SharedViewModelFactory
import com.example.meli.ui.base.ScopedFragment
import okhttp3.internal.Internal.instance
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class DetailFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val sharedViewModelFactory: SharedViewModelFactory by instance()

    lateinit var sharedViewModel: SharedViewModel

    lateinit var selectedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        selectedText = view.findViewById(R.id.selected_text)
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel= ViewModelProviders.of(activity!!, sharedViewModelFactory).get(SharedViewModel::class.java)
        bindUI()
    }

    private fun bindUI(){
        sharedViewModel.selected.observe(this@DetailFragment, Observer {
            selectedText.text = it.toString()
            Log.i("mechi", "selected -> " + it.price.toString())
        })
    }
}
