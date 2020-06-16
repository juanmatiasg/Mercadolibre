package com.example.mercadolibredos.Api

import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api{

    companion object {
         fun getRetrofit(): MercadoLibreApi {
            var retrofit = Retrofit.Builder()
                    .baseUrl("https://api.mercadolibre.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(MercadoLibreApi::class.java)

        }
    }
}