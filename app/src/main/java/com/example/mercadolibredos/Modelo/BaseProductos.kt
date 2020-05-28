package com.example.mercadolibredos.Modelo

import com.google.gson.annotations.SerializedName

class BaseProductos (@SerializedName("query") var query:String,@SerializedName("results") var items:ArrayList<Items>){

}
