package com.example.mercadolibredos.Adapter

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.DescripcionActivity
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import org.w3c.dom.Text
import java.io.Serializable


class ProductosAdapter() : RecyclerView.Adapter<ViewHolder>() {

    var lista: MutableList<Items> = mutableListOf()
    lateinit var context: Context


    fun ProductosAdapter(lista: MutableList<Items>, context: Context) {
        this.lista = lista
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_productos, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = lista.get(position)
        holder.bind(item)


    }


}