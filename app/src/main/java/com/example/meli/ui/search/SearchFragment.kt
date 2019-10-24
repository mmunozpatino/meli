package com.example.meli.ui.search

import android.app.SearchManager
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


import com.example.meli.R
import com.example.meli.data.network.responseClasses.Result
import com.example.meli.ui.Result.ResultFragmentViewModel
import com.example.meli.ui.Result.ResultFragmentViewModelFactory
import com.example.meli.ui.SharedViewModel
import com.example.meli.ui.SharedViewModelFactory
import com.example.meli.ui.adapters.ResultAdapter
import com.example.meli.ui.base.ScopedFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : ScopedFragment(), KodeinAware {

    lateinit var searchItem: MenuItem

    override val kodein: Kodein by closestKodein()

    private val searchProductViewModelFactory: SearchProductViewModelFactory by instance()


    private val sharedViewModelFactory: SharedViewModelFactory by instance()

    lateinit var sharedViewModel: SharedViewModel

//    private lateinit var resultListView: RecyclerView
//
//    private lateinit var resultListAdapter: ResultAdapter

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
//        resultListView = view.findViewById(R.id.result_list)
//        resultListView.layoutManager = LinearLayoutManager(activity)

        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            activity!!, searchProductViewModelFactory).get(SearchViewModel::class.java)

        sharedViewModel= ViewModelProviders.of(activity!!, sharedViewModelFactory).get(SharedViewModel::class.java)
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
//                Log.i("mechi", "on text submit")
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
//        return true
    }


     fun search(query: String)= launch{

         viewModel.q = query

         val response = viewModel.result.await()
         response.observe(this@SearchFragment, Observer{
             Log.i("mechi" , "observe search " +it.results.get(0).price)
             if(it == null) return@Observer

             sharedViewModel._results.value = it.results as ArrayList<Result>
             if (Navigation.findNavController(view!!).currentDestination?.id == R.id.search_fragment) {
                 Navigation.findNavController(view!!).navigate(R.id.next_action)
             }
         })

    }


}
