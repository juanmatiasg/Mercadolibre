package com.example.mercadolibredos.Activities


import android.content.Context
import android.content.Intent

import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo

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

import com.example.mercadolibredos.R
import com.example.mercadolibredos.Utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_search.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    var mAdapter: ProductosAdapter = ProductosAdapter()
    lateinit var toolbar: Toolbar

    private var currentSearch: BaseProductos? = null
    private var currentSearchTerm: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) /*Sacar el titulo por defecto*/

        search("Pc")
        setUpRecyclerView()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (currentSearch != null) {
            outState.putString(CURRENT_SEARCH_KEY, Gson().toJson(currentSearch))
        }
        outState.putString(CURRENT_SEARCH_TERM, currentSearchTerm)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(CURRENT_SEARCH_KEY)) {

            val currentSearchJson = savedInstanceState.getString(CURRENT_SEARCH_KEY)

            currentSearch = Gson().fromJson(currentSearchJson, BaseProductos::class.java)

            if (currentSearch != null) {
                setList(currentSearch!!)
            }
        }
        currentSearchTerm = savedInstanceState.getString(CURRENT_SEARCH_TERM, "")
        search_input_text.setText(currentSearchTerm)

    }

    private fun setList(body:BaseProductos){
        if(body.items.size>0) {
            mAdapter.lista = body.items
            mAdapter.notifyDataSetChanged()
        }

    }




    private fun setUpRecyclerView() {

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


    /*fun buscarPorId(query: String) { /*Funciona*/
        hideKeyboard() /*Oculto el teclado cuando busco por id*/
        Api().searchById(query, object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                if (response.isSuccessful) {
                    var jsonRespuesta = response.body() as Items
                    var lista: ArrayList<Items> = ArrayList()
                    lista.add(jsonRespuesta)

                    mAdapter.ProductosAdapter(lista)
                    mRecyclerView.adapter = mAdapter


                }

            }


        })

    }*/


    private fun search(term: String) { /*Funciona*/

        currentSearchTerm = term

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {

            hideKeyboard() /*Oculta el teclado cuando lo busco*/
            imageViewError.visibility =
                View.INVISIBLE      /*Setea el imageError que no sea Visible*/
            textViewError.visibility =
                View.INVISIBLE       /*Setea el textViewError que no sea Visible*/
            recyclerView.visibility =
                View.INVISIBLE        /*Setea que el recyclerView que no sea visible*/
            progressBar.visibility =
                View.VISIBLE          /*Setea el progressbar que sea Visible*/


            Api().search(currentSearchTerm, object : Callback<BaseProductos> {
                override fun onFailure(call: Call<BaseProductos>, t: Throwable) {

                    progressBar.visibility =
                        View.INVISIBLE

                    imageViewError.visibility = View.VISIBLE
                    textViewError.visibility = View.VISIBLE

                    Toast.makeText(
                        this@MainActivity,
                        ERRORCONNECTION,  /*En tal caso que la busqueda falle por una mala senal, me aparece el mensaje Hubo error de Conexion*/
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onResponse(
                    call: Call<BaseProductos>,
                    response: Response<BaseProductos>
                ) {
                    if (response.isSuccessful) {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE

                        currentSearch = response.body()
                        val lista = currentSearch!!.items
                        //val productoRespuesta = response.body() as BaseProductos
                       // val lista = productoRespuesta.items

                        mAdapter.ProductosAdapter(lista)
                        mRecyclerView.adapter = mAdapter

                    }


                }


            })


        } else {
            recyclerView.visibility = View.INVISIBLE
            textViewError.visibility = View.INVISIBLE
            imageViewError.visibility = View.VISIBLE
            Snackbar.make(
                recyclerView,
                NO_HAY_CONEXION,
                Snackbar.LENGTH_LONG
            ).show()
        }

        execute_search_button.setOnClickListener { search(search_input_text.text.toString()) }


        /*search(busco lo que me interesa)*/
    }  /*Cuando abro la app , desaparece el recycle,mensaje de error, carga el progressbar
       y si fue un exito desparece el progresbar y aparece el recyclerView*/

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
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }

    private fun obtenerCarrito() {
        val intent = Intent(this, CarritoActivity::class.java)
        startActivity(intent)
    }


    companion object {
        var TAG: String = "POKEDEX"
        const val NO_HAY_CONEXION = "No se pudo conectar, por favor vuelva a intentarlo mas tarde"
        const val ERRORCONNECTION = "Hubo un error de conexion"
        val CURRENT_SEARCH_KEY = "CURRENT_SEARCH_KEY"
        val CURRENT_SEARCH_TERM = "CURRENT_SEARCH_TERM"

    }


}






