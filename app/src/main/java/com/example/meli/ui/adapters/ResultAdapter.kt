package com.example.meli.ui.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meli.R
import com.example.meli.data.network.responseClasses.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.result_item.view.*
import java.io.InputStream
import java.net.URL

class ResultAdapter (val items : ArrayList<Result>, val context: Context): RecyclerView.Adapter<ResultAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.result_item, parent, false)
        var vh = ViewHolder(view)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        holder.title!!.text = items.get(position).title
        holder.price!!.text = "$" + items.get(position).price.toString()
//        var input: InputStream = URL(items.get(position).thumbnail).openConnection().getInputStream()

        Picasso.with(context).load(items.get(position).thumbnail).into(holder.image)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        var image: ImageView? = null
        var title: TextView? = null
        var price: TextView? = null

        init {
            image= view.image_product
            title= view.product_title
            price= view.product_price

        }

    }


}