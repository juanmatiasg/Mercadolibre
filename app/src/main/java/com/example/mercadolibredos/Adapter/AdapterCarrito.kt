package com.example.mercadolibredos.Adapter


import android.content.Context
import android.content.Intent


import android.view.*
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_carrito.view.*
import kotlinx.android.synthetic.main.item_carrito.view.*


class AdapterCarrito : RecyclerView.Adapter<ViewHolderCarrito>() {
    private var lista: MutableList<Items> = mutableListOf()


    fun AdapterCarrito(lista: MutableList<Items>) {
        this.lista = lista

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCarrito {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderCarrito(layoutInflater.inflate(R.layout.item_carrito, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolderCarrito, position: Int) {
        holder.id.setText(ProductosAdapter.ID + lista.get(position).id)
        holder.title.setText(ProductosAdapter.TITLE + lista.get(position).title)
        holder.price.setText(ProductosAdapter.PRECIO + lista.get(position).price.toString() + "$")
        holder.photos.loadUrl(lista.get(position).thumbnail.replace("http", "https"))

        /*---------------------------btnEliminar-------------------------------------------*/
        holder.btnEliminar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                var totalRestante: Double = 0.0

                totalRestante -= lista.get(position).price

                lista.remove(lista.get(position))

                holder.wirteListINPref(lista)/*Guardar lista*/

                holder.itemView.textViewTotal.setText(totalRestante.toString()) /*Cuando elimino el producto lamo al itemView para restar el precio de la lista */


            }


        })

        /*--------------------Hago click al item y me lleva a otra Activity-----------------------------*/
        holder.cardView.setOnClickListener(object :
            View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {

                var intent = Intent(v.context, DescripcionActivity::class.java)
                intent.putExtra(ProductosAdapter.TITLE, holder.title.text.toString())
                intent.putExtra(ProductosAdapter.PRECIO, holder.price.text.toString()+"$")
                intent.putExtra(ProductosAdapter.ID,lista.get(position).id)

                v.context.startActivity(intent)


            }

        })


    }


    private fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewCarrito)

    }


}

