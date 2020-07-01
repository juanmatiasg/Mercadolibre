package com.example.mercadolibredos.Adapter


import android.content.Context
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.google.gson.Gson


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewId)
    var title = view.findViewById<TextView>(R.id.textViewTItle)
    var price = view.findViewById<TextView>(R.id.textViewPrecio)
    var photos = view.findViewById<ImageView>(R.id.imageView)
    var cardView = view.findViewById<CardView>(R.id.cardView)
    var check = view.findViewById<CheckBox>(R.id.checkbox)
    var btnAgregar = view.findViewById<Button>(R.id.btnAgregar)


    val LIST_KEY: String = "list_key"

    /*Guarda el estado de la Lista*/
    fun wirteListINPref(list:MutableList<Items>) {


        val gson = Gson()
        val jsonString = gson.toJson(list)

        val prefs = check.context.getSharedPreferences(
            check.context.getString(R.string.shared_key),
            Context.MODE_PRIVATE
        )

        val editor = prefs.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()

    }



}





