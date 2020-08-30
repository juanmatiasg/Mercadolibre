package com.example.mercadolibredos.Modelo

import com.google.gson.annotations.SerializedName

class Pictures(
    @SerializedName("id") var id: String,
    @SerializedName("url") var url: String,
    @SerializedName("secure_url") var secure_url: String
) {

}
