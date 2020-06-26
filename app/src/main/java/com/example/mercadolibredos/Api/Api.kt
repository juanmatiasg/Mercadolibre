package com.example.mercadolibredos.Api

import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    private fun getRetrofit(): MercadoLibreApi {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MercadoLibreApi::class.java)

    }

    fun search(term:String,callback: Callback<BaseProductos>){
        getRetrofit().searching(term).enqueue(callback) /*Busco los productos que me interesa*/
    }

    fun searchById(term:String,callback: Callback<Items>){ /*Busco por id*/
        getRetrofit().search(term).enqueue(callback)
    }

    fun getPictures(id:String,callback: Callback<Items>){
        getRetrofit().getPictures(id).enqueue(callback)
    }

    fun getDescriptions(id:String,callback: Callback<Descripcion>){
        getRetrofit().getAllDescriptions(id).enqueue(callback)
    }


}