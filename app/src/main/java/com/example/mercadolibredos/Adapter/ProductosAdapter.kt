package com.example.mercadolibredos.Adapter

import android.content.Context
import android.content.Intent


import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_productos.view.*


class ProductosAdapter : RecyclerView.Adapter<ViewHolder>() {

    var listaCarritos: MutableList<Items> = mutableListOf()
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
        //holder.bind(item, position)
        holder.id.setText("ID: "+lista.get(position).id)
        holder.title.setText("Titulo: "+lista.get(position).title)
        holder.price.setText("Precio: "+lista.get(position).price.toString()+"$")
        holder.photos.loadUrl(lista.get(position).thumbnail)
        holder.cardView.setOnClickListener(object : View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {

                var intent = Intent(v.context, DescripcionActivity::class.java)

                intent.putExtra("Title", holder.title.text.toString())
                intent.putExtra("Price",  holder.price.text.toString())
                intent.putExtra("id",lista.get(position).id)
                v.context.startActivity(intent)

            }

        })

        val sharedPreferences = holder.check.context.getSharedPreferences(
            holder.check.context.getString(R.string.shared_key),
            Context.MODE_PRIVATE
        )
        holder.check.isChecked = sharedPreferences.getBoolean(lista.get(position).id, false)

        holder.check.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val sharedPreferences = holder.check.context.getSharedPreferences(holder.check.context.getString(R.string.shared_key), Context.MODE_PRIVATE)

                val editor = sharedPreferences.edit()

                if (holder.check.isChecked) {/*Si el item esta chqueada , le agregao a Favoritos*/
                    listasChequeadas.add(item) /*Funciona*/
                    editor.putBoolean(item.id, true) /*Guarda el checkbox true*/
                    PrefConfig.wirteListINPref(context, listasChequeadas) /*Guarda el estado de la lista*/

                } else {
                    listasChequeadas.remove(item)/*Funciona*/
                    editor.putBoolean(item.id, false) /*Guarda el checkbox false*/
                    PrefConfig.wirteListINPref(context, listasChequeadas) /*Guarda el estado de la lista */

                }

                editor.apply()

            }

        })
        /*Btn Agregar Carrito*/
        holder.btnAgregar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(v.context, "Se agrego al Carrito", Toast.LENGTH_SHORT).show()
                listaCarritos.add(item)
                PrefConfig.wirteListINPref(context, listaCarritos) /*Guardo la lista que agrego en el carrito*/

            }

        })

    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageView)

    }


}


