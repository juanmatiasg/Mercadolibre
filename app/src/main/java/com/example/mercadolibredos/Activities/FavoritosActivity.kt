package com.example.mercadolibredos.Activities

import android.content.Context
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterFavoritos
import com.example.mercadolibredos.Adapter.ProductosAdapter

import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class FavoritosActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    var mAdapter:AdapterFavoritos = AdapterFavoritos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        setUpRecyclerView()


    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViewFavoritos)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        /*var prefs = this.getSharedPreferences(this.getString(R.string.shared_key), Context.MODE_PRIVATE)

        var jsonString: String? = prefs.getString(LIST_KEY, "")

        val type = object : TypeToken<MutableList<Items>>() {}.type /*Averiguar que es*/

        var gson = Gson()

        var list: MutableList<Items> = gson.fromJson(jsonString, type)

        mAdapter.AdapterFavoritos(list,this)
        mRecyclerView.adapter=mAdapter*/
    }










}
