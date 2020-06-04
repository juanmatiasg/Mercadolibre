package com.example.mercadolibredos

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mercadolibredos.Adapter.ViewHolder
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_descripcion.*
import kotlinx.android.synthetic.main.item_productos.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class DescripcionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        var bundle: Bundle? = intent.extras

        var title: String? = bundle!!.getString("Title")
        var price: String? = bundle.getString("Price")
        var Image: String? = bundle.getString("Image")
       //var Descripcion: String? = bundle.getString("Descripcion")

        textViewTitleDescripcion.setText(title)
        textViewPriceDescripcion.setText(price)
        Picasso.get().load(Image).into(imageViewDescripcion)
        //textViewDescripcion.setText(Descripcion)

    }


}



