package com.example.mercadolibredos.Adapter

import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.R

class ViewHolderFavoritos(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewIdFavoritos)
    var title = view.findViewById<TextView>(R.id.textViewTitleFavoritos)
    var price = view.findViewById<TextView>(R.id.textViewPrecioFavoritos)
    var photos = view.findViewById<ImageView>(R.id.imageViewFavoritos)
    var cardView = view.findViewById<CardView>(R.id.cardViewFavoritos)
}






