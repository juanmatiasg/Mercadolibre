package com.example.mercadolibredos.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R

class ProductosAdapter:RecyclerView.Adapter<ViewHolder>() {

    var lista:MutableList<Items> = mutableListOf()
    lateinit var context: Context

    fun ProductosAdapter(lista:MutableList<Items>,context: Context){
        this.lista = lista
        this.context=context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_productos,parent,false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = lista.get(position)
        holder.bind(item)
    }

    fun actualizar(lista:MutableList<Items>){
        this.lista=lista
        notifyDataSetChanged()
    }
}