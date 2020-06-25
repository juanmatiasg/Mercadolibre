package com.example.mercadolibredos.Activities


import android.content.Intent

import android.content.res.Configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.*
import androidx.appcompat.widget.Toolbar

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
import com.example.mercadolibredos.Api.Api

import com.example.mercadolibredos.Modelo.BaseProductos
import com.example.mercadolibredos.Modelo.Items

import com.example.mercadolibredos.R
import com.example.mercadolibredos.Utils.hideKeyboard

import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        search("Cualquier Articulo")
        //searchAction.setOnClickListener { search(searchText.text.toString()) } /*Metodo donde se ejecuta la busqueda  */
        setUpRecyclerView()
       // obtenerTodo()  /*Metodo que obtiene todos los Productoss del API*/

    }

    fun setUpRecyclerView() {

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        if (baseContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            mRecyclerView.layoutManager =
                LinearLayoutManager(this) /*Si esta en orientacion Portrait aplica un LinearLayout*/
        } else {

            mRecyclerView.layoutManager = GridLayoutManager(
                this,
                2
            ) /*En el caso Contrario aplica El Grid layout con 2 columnas */
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


   /* private fun obtenerTodo() { /*Funciona*/

        progressBar.visibility = View.VISIBLE

        var servicio = Api.getRetrofit()

        servicio.getAll().enqueue(object : Callback<BaseProductos> {
            override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                progressBar.visibility = View.INVISIBLE
                imageViewError.visibility = View.VISIBLE
                textViewError.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<BaseProductos>,
                response: Response<BaseProductos>
            ) {
                if (response.isSuccessful) {

                    progressBar.visibility = View.INVISIBLE

                    var productoRespuesta = response.body() as BaseProductos
                    var lista = productoRespuesta.items

                    mAdapter.ProductosAdapter(lista, this@MainActivity)
                    mRecyclerView.adapter = mAdapter

                }


            }


        })


    }*/


    private fun search(term: String) { /*Funciona*/

            hideKeyboard() /*Oculta el teclado cuando lo busco*/
            imageViewError.visibility = View.INVISIBLE      /*Setea el imageError que no sea Visible*/
            textViewError.visibility = View.INVISIBLE       /*Setea el textViewError que no sea Visible*/
            recyclerView.visibility = View.INVISIBLE        /*Setea que el recyclerView que no sea visible*/
            progressBar.visibility = View.VISIBLE          /*Setea el progressbar que sea Visible*/


            var service = Api.getRetrofit()
            service.searching(term).enqueue(object : Callback<BaseProductos> {
                override fun onFailure(call: Call<BaseProductos>, t: Throwable) {
                    progressBar.visibility = View.INVISIBLE    /*Cuando no hay conexion el progressbar desapacee y setea la imageViewError , textViewError*/
                    imageViewError.visibility = View.VISIBLE
                    textViewError.visibility = View.VISIBLE

                    Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<BaseProductos>,
                    response: Response<BaseProductos>
                ) {
                    if (response.isSuccessful) {

                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE

                        var productoRespuesta = response.body() as BaseProductos
                        var lista = productoRespuesta.items

                        mAdapter.ProductosAdapter(lista, this@MainActivity)
                        mRecyclerView.adapter = mAdapter
                        buscarPorId(term) /*Llamo al metodo buscar por ID*/

                    }


                }


            }) /*Primero muestra los resultados que pase por Parametro y depues ejecuto la funcion searchAction*/

        searchAction.setOnClickListener { search(searchText.text.toString()) }
                                        /*search(busco lo que me interesa)*/
    }                                   /*Cuando abro la app , desaparece el recycle,mensaje de error, carga el progressbar y si fue un exito desparece el progresbar y aparece el recyclerView*/

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


    private fun obtenerFavoritos() {
        var intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }

    fun obtenerCarrito() {
        var intent = Intent(this, CarritoActivity::class.java)
        startActivity(intent)
    }


    companion object {
        var TAG: String = "POKEDEX"
    }


}






