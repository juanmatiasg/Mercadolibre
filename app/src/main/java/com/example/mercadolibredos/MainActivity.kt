package com.example.mercadolibredos

import android.app.Activity
import android.app.IntentService
import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DeadObjectException
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.Adapter.ViewHolder
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import kotlinx.android.synthetic.main.activity_descripcion.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_productos.*
import okhttp3.RequestBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    var mAdapter: ProductosAdapter = ProductosAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchAction.setOnClickListener { search(searchText.text.toString()) } /*Metodo donde se ejecuta la busqueda  */
        setUpRecyclerView()
        obtenerTodo()  /*Metodo que obtiene todos los Productoss del API*/

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

    companion object {
        var TAG: String = "POKEDEX"
    }




    fun buscarPorId(query: String) { /*Funciona*/
        hideKeyboard() /*Oculto el teclado cuando busco por id*/
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


                }

            }


        })

    }


    fun obtenerTodo() { /*Funciona*/

        var servicio = getRetrofit().create(MercadoLibreApi::class.java)
        servicio.getAll().enqueue(object : Callback<BaseProductos> {
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseProductos>, response: Response<BaseProductos>) {
                if (response.isSuccessful) {

                    var productoRespuesta = response.body() as BaseProductos
                    var lista = productoRespuesta.items

                    mAdapter.ProductosAdapter(lista, this@MainActivity)
                    mRecyclerView.adapter = mAdapter
                }


            }


        })

    }


    private fun search(term: String) { /*Funciona*/


        hideKeyboard() /*Oculta el teclado cuando lo busco*/

        var service = getRetrofit().create(MercadoLibreApi::class.java)
        service.searching(term).enqueue(object : Callback<BaseProductos> {
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No se han encontrado el Articulo", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BaseProductos>, response: Response<BaseProductos>) {
                if (response.isSuccessful) {
                    hideKeyboard()
                    var productoRespuesta = response.body() as BaseProductos
                    var lista = productoRespuesta.items

                    mAdapter.ProductosAdapter(lista, this@MainActivity)
                    mRecyclerView.adapter = mAdapter
                    buscarPorId(term) /*Llamo al metodo buscar por ID*/

                }


            }


        })


    }


    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))

    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }


}






