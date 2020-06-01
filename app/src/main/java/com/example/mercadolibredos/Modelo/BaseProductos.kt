package com.example.mercadolibredos.Modelo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class BaseProductos(@SerializedName("query") var query: String, @SerializedName("results") var items: ArrayList<Items>) {


}
