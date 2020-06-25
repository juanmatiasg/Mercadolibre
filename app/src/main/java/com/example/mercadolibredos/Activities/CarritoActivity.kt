package com.example.mercadolibredos.Activities


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterCarrito
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig

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

        var lista = PrefConfig.readListFromPrefs(this) /*LLamo al preference y obtengo los datos*/


        for(i in lista) {
            sumatoria += i.price
        }

        textViewTotal.setText("Total: ${sumatoria}$")
        mAdapter.AdapterCarrito(lista,this)
        mRecyclerView.adapter = mAdapter

    }





}
