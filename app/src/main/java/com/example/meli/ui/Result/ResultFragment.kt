package com.example.meli.ui.Result

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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.meli.R
import com.example.meli.data.network.responseClasses.Result
import com.example.meli.ui.SharedViewModel
import com.example.meli.ui.SharedViewModelFactory
import com.example.meli.ui.adapters.ResultAdapter
import com.example.meli.ui.base.ScopedFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ResultFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    private lateinit var sharedViewModel: SharedViewModel

    private val sharedFragmentViewModelFactory: SharedViewModelFactory by instance()

    private lateinit var resultListView: RecyclerView

    private lateinit var resultListAdapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        resultListView = view.findViewById(R.id.result_list)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProviders.of(activity!!, sharedFragmentViewModelFactory).get(SharedViewModel::class.java)
        bindUI()
    }

    private fun bindUI(){
        sharedViewModel.results.observe(this@ResultFragment, Observer {
        createResultList(it)
        })
    }

    fun createResultList(list: ArrayList<Result>){
        resultListAdapter = ResultAdapter(list, activity!!.applicationContext, object : ResultClickListener{
            override fun onClick(vista: View, position: Int) {
                sharedViewModel._selected.value = list.get(position)
                Navigation.findNavController(view!!).navigate(R.id.next_action)
            }

        })
        resultListView.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter= resultListAdapter
        }
    }
}
