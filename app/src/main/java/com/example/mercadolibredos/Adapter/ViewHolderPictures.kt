package com.example.mercadolibredos.Adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.R

class ViewHolderPictures(view: View) : RecyclerView.ViewHolder(view) {
    var imagesPictures = view.findViewById<ImageView>(R.id.imageViewPictures)
}
