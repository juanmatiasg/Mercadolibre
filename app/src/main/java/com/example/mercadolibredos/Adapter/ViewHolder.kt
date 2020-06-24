package com.example.mercadolibredos.Adapter


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.transition.Slide
import android.util.Log
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Activities.MainActivity
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.google.gson.Gson

import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_productos.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.FieldPosition
import java.util.ArrayList

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewId)
    var title = view.findViewById<TextView>(R.id.textViewTItle)
    var price = view.findViewById<TextView>(R.id.textViewPrecio)
    var photos = view.findViewById<ImageView>(R.id.imageView)
    var cardView = view.findViewById<CardView>(R.id.cardView)
    var check = view.findViewById<CheckBox>(R.id.checkbox)
    var btnAgregar = view.findViewById<Button>(R.id.btnAgregar)



}






