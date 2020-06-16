package com.example.mercadolibredos.Modelo

import android.content.ClipData
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


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
)



