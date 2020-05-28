package com.example.mercadolibredos.Modelo

import com.google.gson.annotations.SerializedName

class Items(
    @SerializedName("id")
    var id:String,
    @SerializedName("title")
    var title:String,
    @SerializedName("price")
    var price:Double,
    @SerializedName("thumbnail")
    var thumbnail:String
) {

}
