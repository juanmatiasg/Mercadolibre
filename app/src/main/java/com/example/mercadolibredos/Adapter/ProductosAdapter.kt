package com.example.mercadolibredos.Adapter

import android.content.Context


import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R


class ProductosAdapter : RecyclerView.Adapter<ViewHolder>() {
    var listasChequeadas: MutableList<Items> = mutableListOf()
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
        holder.check.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (holder.check.isChecked) {/*Si el item esta chqueada , le agregao a Favoritos*/
                //    prefs.edit().putBoolean("checkbox",holder.check.isChecked)
                    listasChequeadas.add(item) /*Funciona*/

                } else {
                    listasChequeadas.remove(item) /*Funciona*/
                }


            }

        })


    }


}


