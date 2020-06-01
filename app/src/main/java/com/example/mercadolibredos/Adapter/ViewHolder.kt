package com.example.mercadolibredos.Adapter

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.DescripcionActivity
import com.example.mercadolibredos.MainActivity
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_productos.view.*
import java.io.Serializable

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewId)
    var title = view.findViewById<TextView>(R.id.textViewTItle)
    var price = view.findViewById<TextView>(R.id.textViewPrecio)
    var photos = view.findViewById<ImageView>(R.id.imageView)
    var descripcion = view.findViewById<TextView>(R.id.textViewDescripcion)

    var cardView = view.findViewById<CardView>(R.id.cardView)

    fun bind(json: Items) {
        id.text = "ID: " + json.id
        title.text = "Title: " + json.title
        price.text = "Price: " + json.price.toString() + "$"
        photos.loadUrl(json.thumbnail)

        // descripcion = json.descriptions.get(ProductosAdapter)

       cardView.setOnClickListener(object : View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {
                var intent = Intent(v.context, DescripcionActivity::class.java)
                intent.putExtra("Image", json.thumbnail)
                intent.putExtra("Title", title.text.toString())
                intent.putExtra("Price", price.text.toString())

               // intent.putExtra("Descripcion",json.descriptions.get(v.id).plain_text)

                /*Pasar los datos extra a Bundle a la clase DescripcionActivity*/

                v.context.startActivity(intent) /*otra Activity(Descripcion)*/

            }

        })

    }


    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageView)
    }


}
