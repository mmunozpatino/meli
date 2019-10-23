package com.example.meli.ui

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.example.meli.R
import com.example.meli.ui.search.SearchFragment
import com.example.meli.ui.search.SearchProductViewModelFactory
import com.example.meli.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.Internal.instance
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    lateinit var searchItem: MenuItem

    override val kodein: Kodein by closestKodein()

    private val searchProductViewModelFactory: SearchProductViewModelFactory by instance()

    private lateinit var viewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this, searchProductViewModelFactory).get(SearchViewModel::class.java)

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        val inflater = menuInflater
//        inflater.inflate(R.menu.main_menu, menu)
//
//        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchItem = menu?.findItem(R.id.search)!!
//
//        val searchView = searchItem.actionView as SearchView
//
//
//
//        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
//
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.i("mechi", "on text submit")
////                searchItem.collapseActionView()
//                searchProduct(query!!)
//
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//
//
//        })
//
//        return true
//    }


    fun search(){
        searchItem.expandActionView()
    }

    fun searchProduct(query: String)= GlobalScope.launch {
        Log.i("mechi", "query on activity -> "+ query)
        viewModel._query.postValue(query)
    }
}

