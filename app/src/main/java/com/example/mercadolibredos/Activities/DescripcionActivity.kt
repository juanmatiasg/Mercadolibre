package com.example.mercadolibredos.Activities

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterPictures
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
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

    var mAdapter:AdapterPictures = AdapterPictures()
    lateinit var mRecyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        var bundle: Bundle? = intent.extras

        var title: String? = bundle!!.getString("Title")
        var price: String? = bundle.getString("Price")


        textViewTitleDescripcion.setText(title)
        textViewPriceDescripcion.setText(price)


        obtenerDescripcion()
        obtenerPictures()



    }

    fun obtenerDescripcion(){
        var bundle = intent.extras
        var id :String? = bundle!!.getString("id")
        Api().getDescriptions(id!!,object :Callback<Descripcion>{
            override fun onFailure(call: Call<Descripcion>, t: Throwable) {
                Log.i(MainActivity.TAG,"No hay descripcion")
            }

            override fun onResponse(call: Call<Descripcion>, response: Response<Descripcion>) {
                var respuesta = response.body() as Descripcion
                textViewDescripcion.setText(respuesta.plain_text)
            }

        })
    }

    fun obtenerPictures(){
        mRecyclerView = findViewById(R.id.recyclerViewPictures)
        mRecyclerView.hasFixedSize()
        mRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        var bundle: Bundle? = intent.extras
        var id :String? = bundle!!.getString("id")

        Api().getPictures(id!!,object:Callback<Items>{
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.i(MainActivity.TAG,"No hay Fotos")
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                var respuesta = response.body() as Items
                var lista:MutableList<Pictures> = respuesta.pictures

                mAdapter.AdapterPictures(lista)
                mRecyclerView.adapter = mAdapter
            }

        })
    }




}



