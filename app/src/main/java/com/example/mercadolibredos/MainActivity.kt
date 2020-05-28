package com.example.mercadolibredos

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DeadObjectException
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Items

import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var mRecyclerView: RecyclerView
    var mAdapter: ProductosAdapter = ProductosAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView.setOnQueryTextListener(this)
        setUpRecyclerView()
        //  obtenerTodo()
        obtenerTodo()

    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    /*fun buscarPorId(query: String) { /*Funciona*/
        var service = getRetrofit().create(MercadoLibreApi::class.java)
        service.search(query).enqueue(object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                if (response.isSuccessful) {
                    var jsonRespuesta = response.body() as Items
                    var lista: ArrayList<Items> = ArrayList()
                    lista.add(jsonRespuesta)

                    mAdapter.ProductosAdapter(lista, this@MainActivity)
                    mRecyclerView.adapter = mAdapter

                } else {
                    Toast.makeText(this@MainActivity, "No se ha cargado los datos", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }*/

    fun buscarTodo(query: String){ /*Se busca todos los objetos */
        var servicioTres = getRetrofit().create(MercadoLibreApi::class.java)
        servicioTres.searching(query).enqueue(object :Callback<BaseProductos>{
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Producto no disponible",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseProductos>, response: Response<BaseProductos>) {
                var productoRespuesta = response.body() as BaseProductos
                var lista = productoRespuesta.items

                mAdapter.ProductosAdapter(lista,this@MainActivity)
                mRecyclerView.adapter=mAdapter
            }

        })
    }

    fun obtenerTodo() { /*Correcto*/
        var servicio = getRetrofit().create(MercadoLibreApi::class.java)
            servicio.getAll().enqueue(object : Callback<BaseProductos> {
                    override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                        Toast.makeText(this@MainActivity,"No hay conexion",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<BaseProductos>, response: Response<BaseProductos>) {
                        if (response.isSuccessful) {
                            var productoRespuesta = response.body() as BaseProductos
                            var lista = productoRespuesta.items

                            mAdapter.ProductosAdapter(lista,this@MainActivity)
                            mRecyclerView.adapter=mAdapter

                        }
                    }


                })
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        //buscarPorId(query)
        buscarTodo(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        obtenerTodo()
        return true
    }




    /*fun obtenerTodo() {
        var service = getRetrofit().create(MercadoLibreApi::class.java)
        service.getAll().enqueue(object : Callback<BaseProductos> {
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                    call: Call<BaseProductos>,
                    response: Response<BaseProductos>
            ) {
                if (response.isSuccessful) {
                    var jsonRespuesta = response.body() as BaseProductos
                    var lista: MutableList<Items> = jsonRespuesta.items

                    mAdapter.ProductosAdapter(lista, this@MainActivity)
                    mRecyclerView.adapter = mAdapter
                } else {
                    Toast.makeText(this@MainActivity, "No hay datos", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }*/


}



