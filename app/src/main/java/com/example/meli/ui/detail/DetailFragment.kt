package com.example.meli.ui.detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import android.graphics.BitmapFactory
import com.squareup.picasso.Picasso
import java.net.URL


class DetailFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val sharedViewModelFactory: SharedViewModelFactory by instance()

    lateinit var sharedViewModel: SharedViewModel

    lateinit var image: ImageView

    lateinit var amountSold: TextView

    lateinit var title: TextView

    lateinit var price: TextView

    lateinit var stockStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.example.meli.R.layout.fragment_detail, container, false)

        image= view.findViewById(com.example.meli.R.id.result_img)
        amountSold = view.findViewById(com.example.meli.R.id.tv_amount_sold)
        title= view.findViewById(com.example.meli.R.id.tv_title)
        price= view.findViewById(com.example.meli.R.id.tv_price)
        stockStatus = view.findViewById(R.id.tv_stock_status)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel= ViewModelProviders.of(activity!!, sharedViewModelFactory).get(SharedViewModel::class.java)
        bindUI()
    }

    private fun bindUI(){
        sharedViewModel.selected.observe(this@DetailFragment, Observer {
            var condition = ""
            var stock = ""
            if(it.condition == "new") {
                condition = "Nuevo"
            }else{
                condition = "Usado"
            }

            if(it.availableQuantity != 0){
                stock = "Stock Disponible"
            }else{
                stock = "Sin Stock Disponible"
            }

            title.text = it.title
            amountSold.text = condition + " - " + it.soldQuantity.toString() + " vendidos"
            price.text = "$ "+it.price.toString()
            stockStatus.text = stock
//            val imageUrl = it.thumbnail
//            val img = URL(imageUrl).openStream()
//            image.setImageBitmap(BitmapFactory.decodeStream(img))

            Picasso.with(context).load(it.thumbnail).into(image)
        })
    }
}
