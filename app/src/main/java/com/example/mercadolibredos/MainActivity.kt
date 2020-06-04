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
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
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
        setTheme(R.style.AppTheme) /*setea el SplashScreen*/
        Thread.sleep(3000) /*Tiempo de espera 3000 mili segundos*/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchAction.setOnClickListener { search(searchText.text.toString()) } /*Metodo donde se ejecuta la busqueda  */
        //searchAction.setOnClickListener { buscarPorId(searchText.text.toString()) } /*Metodo donde se ejecuta la busqueda de un solo item*/
        setUpRecyclerView()
        obtenerTodo()  /*Metodo que obtiene todos los Productoss del API*/
        //obtenerDescripcion()

    }

    companion object {
        var TAG: String = "POKEDEX"
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

    fun obtenerDescripcion(query: String) { /*Funciona pero hay que buscar varios id*/
        var service = getRetrofit().create(MercadoLibreApi::class.java)
        service.getAllDescriptions(query).enqueue(object : Callback<Descripcion> {
            override fun onFailure(call: Call<Descripcion>, t: Throwable) {
                Log.i(TAG, "No hay datos")
            }

            override fun onResponse(call: Call<Descripcion>, response: Response<Descripcion>) {
                var respuesta = response.body() as Descripcion
                var lista: ArrayList<Descripcion> = ArrayList()
                lista.add(respuesta) /*Funciona Perfecto*/


                for (i in 0..lista.size) {
                    var d: Descripcion = lista.get(i)

                    Log.i(TAG, d.plain_text)
                }

            }
        })

    }


    fun obtenerTodo() { /*Correcto*/

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


    private fun search(term: String) {
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






