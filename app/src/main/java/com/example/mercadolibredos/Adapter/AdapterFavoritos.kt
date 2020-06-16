package com.example.mercadolibredos.Adapter

import android.content.Context

import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favoritos.view.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AdapterFavoritos : RecyclerView.Adapter<ViewHolderFavoritos>() {
    var lista: MutableList<Items> = mutableListOf()
    lateinit var context: Context

    fun AdapterFavoritos(lista: MutableList<Items>, context: Context) {
        this.lista = lista
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoritos {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderFavoritos(layoutInflater.inflate(R.layout.item_favoritos, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolderFavoritos, position: Int) {
        holder.id.setText(lista.get(position).id)
        holder.title.setText(lista.get(position).title)
        holder.price.setText(lista.get(position).price.toString())
        holder.photos.loadUrl(lista.get(position).thumbnail)

    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewFavoritos)

    }






}

