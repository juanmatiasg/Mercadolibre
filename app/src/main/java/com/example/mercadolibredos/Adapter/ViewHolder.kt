package com.example.mercadolibredos.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_productos.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewId)
    var name = view.findViewById<TextView>(R.id.textViewTItle)
    var price = view.findViewById<TextView>(R.id.textViewPrecio)
    var photos = view.findViewById<ImageView>(R.id.imageView)


    fun bind(json: Items) {
        id.text = "ID: "+json.id
        name.text = "Title: "+json.title
        price.text = "Price: "+json.price.toString()+"$"
        photos.loadUrl(json.thumbnail)
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageView)
    }
}
