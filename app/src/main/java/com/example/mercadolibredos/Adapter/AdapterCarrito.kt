package com.example.mercadolibredos.Adapter


import android.content.Context

import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_carrito.view.*


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
        holder.id.setText(lista.get(position).id)
        holder.title.setText(lista.get(position).title)
        holder.price.setText(lista.get(position).price.toString())
        holder.photos.loadUrl(lista.get(position).thumbnail)
        holder.btnEliminar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                lista.remove(lista.get(position))
                PrefConfig.wirteListINPref(context, lista)/*Guardar lista*/
            }


        }) /*btnEliminar = Elimina el item de Carritos*/


    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewCarrito)

    }


}

