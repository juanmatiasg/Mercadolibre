package com.example.mercadolibredos.Adapter



import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.R

class ViewHolderCarrito(view: View) :RecyclerView.ViewHolder(view) {
    var id = view.findViewById<TextView>(R.id.textViewIdCarrito)
    var title = view.findViewById<TextView>(R.id.textViewTitleCarrito)
    var price = view.findViewById<TextView>(R.id.textViewPrecioCarrito)
    var photos = view.findViewById<ImageView>(R.id.imageViewCarrito)
    var btnEliminar = view.findViewById<Button>(R.id.buttonEliminar)
}