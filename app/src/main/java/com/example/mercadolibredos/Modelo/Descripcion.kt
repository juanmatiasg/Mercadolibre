package com.example.mercadolibredos.Modelo

import com.google.gson.annotations.SerializedName

/*https://api.mercadolibre.com/items/MLA856812678/descriptions*/

class Descripcion(@SerializedName("id") var id: String, @SerializedName("plain_text") var plain_text: String) {

}