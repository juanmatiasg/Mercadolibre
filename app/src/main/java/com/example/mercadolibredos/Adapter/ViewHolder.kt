package com.example.mercadolibredos.Adapter

import android.content.Intent
import android.util.Log
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.DescripcionActivity
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.MainActivity
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_productos.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var id = view.findViewById<TextView>(R.id.textViewId)
    var title = view.findViewById<TextView>(R.id.textViewTItle)
    var price = view.findViewById<TextView>(R.id.textViewPrecio)
    var photos = view.findViewById<ImageView>(R.id.imageView)
    var cardView = view.findViewById<CardView>(R.id.cardView)
    var check = view.findViewById<CheckBox>(R.id.checkbox)



    fun bind(json: Items) {
        id.text = "ID: " + json.id
        title.text = "Titulo: " + json.title
        price.text = "Precio: " + json.price.toString() + "$"
        photos.loadUrl(json.thumbnail)



        cardView.setOnClickListener(object : View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {

                var intent = Intent(v.context, DescripcionActivity::class.java)
                intent.putExtra("Image", json.thumbnail)
                intent.putExtra("Title", title.text.toString())
                intent.putExtra("Price", price.text.toString())


                /*Mando el llamo a la interfaz del API y paso por parametro el id que necesita para
                * obtener la descripcion del producto*/

                var service = getRetrofit().create(MercadoLibreApi::class.java)
                service.getAllDescriptions(json.id).enqueue(object : Callback<Descripcion> {
                    override fun onFailure(call: Call<Descripcion>, t: Throwable) {
                        Log.i(MainActivity.TAG, "No hay datos")
                    }

                    override fun onResponse(call: Call<Descripcion>, response: Response<Descripcion>) {
                        var respuesta = response.body() as Descripcion
                        var lista: ArrayList<Descripcion> = ArrayList()
                        lista.add(respuesta) /*Funciona Perfecto*/

                        intent.putExtra("Descripcion", respuesta.plain_text)
                        v.context.startActivity(intent) /*otra Activity(Descripcion)*/

                    }

                })


            }

        })


    }


    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageView)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }


}






