package com.example.meli.ui.serach

import android.app.SearchManager
import android.content.Context
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService

import com.example.meli.R
import com.example.meli.ui.MainActivity
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : Fragment(), View.OnClickListener {

    lateinit var searchButton: Button

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
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.search_button -> {
                (activity as MainActivity).search()
            }
        }
    }


}
