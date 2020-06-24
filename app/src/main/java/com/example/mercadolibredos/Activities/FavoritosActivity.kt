package com.example.mercadolibredos.Activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.AdapterFavoritos
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.R
import com.example.mercadolibredos.SharedPreferenceListas.PrefConfig
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favoritos.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritosActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    var mAdapter:AdapterFavoritos = AdapterFavoritos()
    var mAdapterProductosAdapter = ProductosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        setUpRecyclerView()


    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViewFavoritos)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        var lista = PrefConfig.readListFromPrefs(this) /*LLamo al preference y obtengo los datos*/
        mAdapter.AdapterFavoritos(lista,this)
        mRecyclerView.adapter=mAdapter
    }










}
