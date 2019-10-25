package com.example.meli.ui.search

import android.app.SearchManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.coroutines.launch


import com.example.meli.R
import com.example.meli.data.network.responseClasses.Result
import com.example.meli.ui.SharedViewModel
import com.example.meli.ui.SharedViewModelFactory
import com.example.meli.ui.base.ScopedFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : ScopedFragment(), KodeinAware, View.OnClickListener {

    lateinit var searchItem: MenuItem

    override val kodein: Kodein by closestKodein()

    private val searchProductViewModelFactory: SearchProductViewModelFactory by instance()


    private val sharedViewModelFactory: SharedViewModelFactory by instance()

    lateinit var sharedViewModel: SharedViewModel

    lateinit var searchButton: Button

    lateinit var progressBar: ProgressBar

    lateinit var body: RelativeLayout


    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        body = view.findViewById(R.id.body)
        searchButton = view.findViewById(R.id.search_btn)
        searchButton.setOnClickListener(this)
        progressBar = view.findViewById(R.id.loading_bar)
        progressBar.visibility = View.GONE

        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            activity!!, searchProductViewModelFactory).get(SearchViewModel::class.java)

        sharedViewModel= ViewModelProviders.of(activity!!, sharedViewModelFactory).get(SharedViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchItem = menu?.findItem(R.id.search)!!

        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel._query.postValue(query!!)
                searchItem.collapseActionView()
                search(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.search_btn -> {
                searchItem.expandActionView()
            }
        }
    }

     fun search(query: String)= launch{
         if(checkConnection()){
             viewModel.q = query

             progressBar.visibility = View.VISIBLE
             body.visibility = View.GONE

             viewModel.search().observe(this@SearchFragment, Observer {
                 sharedViewModel._results.value = it.results as ArrayList<Result>

                 if (Navigation.findNavController(view!!).currentDestination?.id == R.id.search_fragment) {
                     Navigation.findNavController(view!!).navigate(R.id.next_action)
                 }
             })
         }else{
             Toast.makeText(activity, "Sin conexión a internet", Toast.LENGTH_LONG).show()
         }

    }

    private fun checkConnection(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}
