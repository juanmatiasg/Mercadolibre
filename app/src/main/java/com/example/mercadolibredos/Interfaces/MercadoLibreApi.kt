package com.example.mercadolibredos.Interfaces


import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface MercadoLibreApi {


    /*@GET("items/{id}")  /*se buscar un solo producto*/
    fun search(@Path("id") id: String): Call<Items>*/


    @GET("sites/MLA/search?q=")  /*Se busca todos los productos*/
    fun search(@Query("q") q: String): Call<BaseProductos>

    @GET("items/{id}/descriptions/plain_text") /*Obtiene la Descripcion del Producto en ViewHolder*/
    fun getAllDescriptions(@Path("id") id: String): Call<Descripcion>

    @GET("items/{id}")
    fun getPictures(@Path("id") id: String): Call<Items>



}