package com.example.mercadolibredos.SharedPreferenceListas

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures
import com.example.mercadolibredos.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefConfig {

    /*companion object {
        val LIST_KEY: String = "list_key"

        /*Guarda el estado de la Lista*/
       fun wirteListINPref(context: Context, list: MutableList<Items>) {
            var gson = Gson()
            var jsonString = gson.toJson(list)

            var prefs = context.getSharedPreferences(context.getString(R.string.shared_key), Context.MODE_PRIVATE)

            var editor = prefs.edit()
            editor.putString(LIST_KEY, jsonString)
            editor.apply()

        }

        /*Obtengo el estado de la lista */
        fun readListFromPrefs(context: Context): MutableList<Items> {
            var prefs = context.getSharedPreferences(context.getString(R.string.shared_key), Context.MODE_PRIVATE)

            var jsonString: String? = prefs.getString(LIST_KEY, "")

            val type = object : TypeToken<MutableList<Items>>() {}.type /*Averiguar que es*/

            var gson = Gson()

            var list: MutableList<Items> = gson.fromJson(jsonString, type)

            return list
        }


    }*/
}