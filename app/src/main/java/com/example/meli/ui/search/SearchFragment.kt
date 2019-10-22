package com.example.meli.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.coroutines.launch


import com.example.meli.R
import com.example.meli.ui.MainActivity
import com.example.meli.ui.base.ScopedFragment
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : ScopedFragment(), KodeinAware, View.OnClickListener {

    lateinit var searchButton: Button

    override val kodein: Kodein by closestKodein()

    private val searchProductViewModelFactory: SearchProductViewModelFactory by instance()

    private lateinit var textView: TextView

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        searchButton = view.findViewById(R.id.search_button)
        searchButton.setOnClickListener(this)
        textView = view.findViewById(R.id.result)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, searchProductViewModelFactory).get(SearchViewModel::class.java)
        bindUI()
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.search_button -> {
                (activity as MainActivity).search()
            }
        }
    }

     fun bindUI()= launch {
        viewModel.searchResult.observe(this@SearchFragment, Observer {
            textView.text = it.toString()
        })
    }

}
