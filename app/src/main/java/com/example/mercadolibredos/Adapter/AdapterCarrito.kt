package com.example.mercadolibredos.Adapter


import android.content.Context
import android.content.Intent
import android.util.Log

import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Activities.MainActivity
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_carrito.view.*
import kotlinx.android.synthetic.main.item_carrito.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdapterCarrito : RecyclerView.Adapter<ViewHolderCarrito>() {
    var lista: MutableList<Items> = mutableListOf()
    lateinit var context: Context


    fun AdapterCarrito(lista: MutableList<Items>, context: Context) {
        this.lista = lista
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCarrito {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderCarrito(layoutInflater.inflate(R.layout.item_carrito, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolderCarrito, position: Int) {
        holder.id.setText("ID: " + lista.get(position).id)
        holder.title.setText("Title: " + lista.get(position).title)
        holder.price.setText("Precio: " + lista.get(position).price.toString() + "$")
        holder.photos.loadUrl(lista.get(position).thumbnail)

        /*---------------------------btnEliminar-------------------------------------------*/
        holder.btnEliminar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                var totalRestante: Double = 0.0

                totalRestante -= lista.get(position).price

                lista.remove(lista.get(position))

                PrefConfig.wirteListINPref(context, lista)/*Guardar lista*/

                holder.itemView.textViewTotal.setText(totalRestante.toString()) /*Cuando elimino el producto lamo al itemView para restar el precio de la lista */


            }


        })

        /*--------------------Hago click al item y me lleva a otra Activity-----------------------------*/
        holder.cardView.setOnClickListener(object :
            View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {

                var intent = Intent(v.context, DescripcionActivity::class.java)
                intent.putExtra("Image", lista.get(position).thumbnail)
                intent.putExtra("Title", holder.title.text.toString())
                intent.putExtra("Price", "Precio: " + holder.price.text.toString() + "$")
                intent.putExtra("id", lista.get(position).id)
                v.context.startActivity(intent)


            }

        })


    }


    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewCarrito)

    }


}

