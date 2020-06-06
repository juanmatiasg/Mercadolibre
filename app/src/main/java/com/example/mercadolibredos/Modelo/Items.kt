package com.example.mercadolibredos.Modelo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Items(
    @SerializedName("id")
    var id:String,
    @SerializedName("title")
    var title:String,
    @SerializedName("price")
    var price:Double,
    @SerializedName("thumbnail")
    var thumbnail:String,
    @SerializedName("descriptions")
    var descriptions:MutableList<Descripcion>,
    @SerializedName("pictures")
    var pictures:MutableList<Pictures>


) {

}
