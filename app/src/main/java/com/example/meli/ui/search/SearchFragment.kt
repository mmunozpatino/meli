package com.example.meli.ui.search

import android.app.SearchManager
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
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

class SearchFragment : ScopedFragment(), KodeinAware {

    lateinit var searchItem: MenuItem

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
        textView = view.findViewById(R.id.result)
        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, searchProductViewModelFactory).get(SearchViewModel::class.java)
//        bindUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i("mechi", "on create menu")
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchItem = menu?.findItem(R.id.search)!!

        val searchView = searchItem.actionView as SearchView



        searchView.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("mechi", "on text submit")
                viewModel._query.postValue(query!!)
                searchItem.collapseActionView()
                bindUI()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })

        super.onCreateOptionsMenu(menu, inflater)
//        return true
    }


     fun bindUI()= launch {
         val response = viewModel.result.await()
         response.observe(this@SearchFragment, Observer{
             if(it == null) return@Observer

             textView.text = it.toString()
         })
    }

}
