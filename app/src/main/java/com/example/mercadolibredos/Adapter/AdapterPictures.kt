package com.example.mercadolibredos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Modelo.Pictures
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pictures.view.*

class AdapterPictures : RecyclerView.Adapter<ViewHolderPictures>() {
    var images: MutableList<Pictures> = mutableListOf()


    fun AdapterPictures(images: MutableList<Pictures>) {
        this.images = images

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPictures {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderPictures(layoutInflater.inflate(R.layout.item_pictures, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolderPictures, position: Int) {
        val item = images[position]
        holder.imagesPictures.loadUrl(item.url.replace("http","https"))
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewPictures)
    }
}

