package com.example.mercadolibredos.Activities

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterPictures
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures

import com.example.mercadolibredos.R

import kotlinx.android.synthetic.main.activity_descripcion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescripcionActivity : AppCompatActivity() {

    var madapter:AdapterPictures = AdapterPictures()
    lateinit var mrecyclerView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        val bundle: Bundle? = intent.extras

        val title: String? = bundle!!.getString(ProductosAdapter.TITLE)
        val price: String? = bundle.getString(ProductosAdapter.PRECIO)

        textViewTitleDescripcion.setText(title)
        textViewPriceDescripcion.setText(price)

        obtenerDescripcion()
        obtenerPictures()
    }

    private fun obtenerDescripcion(){
        val bundle = intent.extras
        val id :String? = bundle!!.getString(ProductosAdapter.ID)
        Api().getDescriptions(id!!,object :Callback<Descripcion>{
            override fun onFailure(call: Call<Descripcion>, t: Throwable) {
                Log.i(MainActivity.TAG,"No hay descripcion")
            }

            override fun onResponse(call: Call<Descripcion>, response: Response<Descripcion>) {
                val respuesta = response.body() as Descripcion
                textViewDescripcion.setText(respuesta.plain_text)
            }

        })
    }

    private fun obtenerPictures(){

        mrecyclerView = findViewById(R.id.recyclerViewPictures)
        mrecyclerView.hasFixedSize()
        mrecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        val bundle: Bundle? = intent.extras
        val id :String? = bundle!!.getString(ProductosAdapter.ID)

        Api().getPictures(id!!,object:Callback<Items>{
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.i(MainActivity.TAG,"No hay Fotos")
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                val respuesta = response.body() as Items
                val lista:MutableList<Pictures> = respuesta.pictures

                madapter.AdapterPictures(lista)
                mrecyclerView.adapter = madapter

            }

        })
    }




}



