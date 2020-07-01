package com.example.mercadolibredos.Adapter


import android.content.Context

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.google.gson.Gson


class ViewHolderCarrito(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewIdCarrito)
    var title = view.findViewById<TextView>(R.id.textViewTitleCarrito)
    var price = view.findViewById<TextView>(R.id.textViewPrecioCarrito)
    var photos = view.findViewById<ImageView>(R.id.imageViewCarrito)
    var btnEliminar = view.findViewById<Button>(R.id.buttonEliminar)
    var cardView = view.findViewById<CardView>(R.id.cardViewCarrito)


    val LIST_KEY: String = "list_key"

    /*Guarda el estado de la Lista*/
    fun wirteListINPref(list:MutableList<Items>) {


        val gson = Gson()
        val jsonString = gson.toJson(list)

        val prefs = itemView.context.getSharedPreferences(
            itemView.context.getString(R.string.shared_key),
            Context.MODE_PRIVATE
        )

        val editor = prefs.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()

    }
}
