package com.example.mercadolibredos.Activities


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterCarrito

import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarritoActivity : AppCompatActivity() {

    lateinit var mRecyclerView:RecyclerView
    var mAdapter:AdapterCarrito = AdapterCarrito()
    lateinit var textViewTotal:TextView

    var sumatoria:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        textViewTotal = findViewById(R.id.textViewTotal)

        mRecyclerView = findViewById(R.id.recyclerViewCarrito)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


       /* var prefs = this.getSharedPreferences(this.getString(R.string.shared_key), Context.MODE_PRIVATE)

        var jsonString: String? = prefs.getString(LIST_KEY, "")

        val type = object : TypeToken<MutableList<Items>>() {}.type /*Averiguar que es*/

        var gson = Gson()

        var lista: MutableList<Items> = gson.fromJson(jsonString, type)



        for(i in lista) {
            sumatoria += i.price
        }

        textViewTotal.setText("Total: ${sumatoria}$")
        mAdapter.AdapterCarrito(lista,this)
        mRecyclerView.adapter = mAdapter*/

    }





}
