package com.example.mercadolibredos.Adapter


import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Activities.MainActivity
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewHolderCarrito(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewIdCarrito)
    var title = view.findViewById<TextView>(R.id.textViewTitleCarrito)
    var price = view.findViewById<TextView>(R.id.textViewPrecioCarrito)
    var photos = view.findViewById<ImageView>(R.id.imageViewCarrito)
    var btnEliminar = view.findViewById<Button>(R.id.buttonEliminar)
    var cardView = view.findViewById<CardView>(R.id.cardViewCarrito)


}
