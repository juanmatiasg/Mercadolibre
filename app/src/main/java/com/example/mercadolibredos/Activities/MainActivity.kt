package com.example.mercadolibredos.Activities

import android.app.Activity
import android.content.Intent

import android.content.res.Configuration
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Interfaces.MercadoLibreApi
import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.Modelo.Pictures
import com.example.mercadolibredos.R
import com.example.mercadolibredos.Utils.hideKeyboard

import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    lateinit var mRecyclerView: RecyclerView
    var mAdapter: ProductosAdapter = ProductosAdapter()
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) /*Sacar el titulo por defecto*/

        searchAction.setOnClickListener { search(searchText.text.toString()) } /*Metodo donde se ejecuta la busqueda  */
        setUpRecyclerView()
        obtenerTodo()  /*Metodo que obtiene todos los Productoss del API*/

    }


    fun setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        if (baseContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            mRecyclerView.layoutManager = LinearLayoutManager(this) /*Si esta en orientacion Portrait aplica un LinearLayout*/
        } else {

            mRecyclerView.layoutManager = GridLayoutManager(this, 2) /*En el caso Contrario aplica El Grid layout con 2 columnas */
        }
    }


    fun buscarPorId(query: String) { /*Funciona*/
        hideKeyboard() /*Oculto el teclado cuando busco por id*/
        var service = Api.getRetrofit()
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

        var servicio = Api.getRetrofit()

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
        var service = Api.getRetrofit()
        service.searching(term).enqueue(object : Callback<BaseProductos> {
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu) /*inflar el diseno del menu*/
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { /*itemId = es Favorito o Productos*/
            R.id.favoritos -> obtenerFavoritos()/*Obtener un solo objeto chequeados pasado por paramtro*/
            R.id.verCarrito -> obtenerCarrito()
        }

        return true
    }


    fun obtenerFavoritos() {
        var intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }

    fun obtenerCarrito() {
        var intent = Intent(this, CarritoActivity::class.java)
        startActivity(intent)
    }


    /* fun pictures(){ /*Funciona Perecto*/
         var service = Api.getRetrofit()
         service.getPictures("MLA847644305").enqueue(object :Callback<Items>{
             override fun onFailure(call: Call<Items>, t: Throwable) {
                 Log.i(TAG,"No hay fotos ")
             }

             override fun onResponse(call: Call<Items>, response: Response<Items>) {
                 var pic = response.body() as Items
                 var lista:MutableList<Pictures> = pic.pictures

                 for (i in 0..lista.size) {
                     var p = lista.get(i)
                     Log.i(TAG, p.url)
                 }
             }

         })


     }*/

    companion object {
        var TAG: String = "POKEDEX"
    }


}






