package com.example.mercadolibredos.Interfaces


import android.content.ClipData
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.math.sign
interface MercadoLibreApi {


 /*@GET("items")
 fun getAll():Call<BaseProductos>*/

 @GET("items/{id}")  /*se buscar un solo item*/
 fun search(@Path("id")id:String):Call<Items>

 @GET("sites/MLA/search?q=articulos")  /*Obtiene Todos los Articulos*/
 fun getAll():Call<BaseProductos>

 @GET("sites/MLA/search?q=")  /*Correctisimo: Se busca todos los productos*/
 fun searching(@Query("q") q:String):Call<BaseProductos>

}