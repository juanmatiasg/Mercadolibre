package com.example.mercadolibredos.Interfaces


import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.math.sign
interface MercadoLibreApi {




 @GET("items/{id}")  /*se buscar un solo producto*/
 fun search(@Path("id")id:String):Call<Items>

 @GET("sites/MLA/search?q=articulos")  /*Obtiene Todos los Articulos*/
 fun getAll():Call<BaseProductos>

 @GET("sites/MLA/search?q=")  /*Se busca todos los productos*/
 fun searching(@Query("q") q:String):Call<BaseProductos>

    @GET("items/{id}/descriptions/plain_text") /*Obtiene la Descripcion del Producto en ViewHolder*/
    fun getAllDescriptions(@Path("id")id:String):Call<Descripcion>



}