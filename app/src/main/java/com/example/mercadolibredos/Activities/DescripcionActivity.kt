package com.example.mercadolibredos.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures

import com.example.mercadolibredos.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_descripcion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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



