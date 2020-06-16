package com.example.mercadolibredos.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_descripcion.*

class DescripcionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        var bundle: Bundle? = intent.extras

        var title: String? = bundle!!.getString("Title")
        var price: String? = bundle.getString("Price")
        var Image: String? = bundle.getString("Image")
        var Descripcion: String? = intent.getStringExtra("Descripcion")

        textViewTitleDescripcion.setText(title)
        textViewPriceDescripcion.setText(price)
        Picasso.get().load(Image).into(imageViewDescripcion)
        textViewDescripcion.setText(Descripcion)


    }
}



