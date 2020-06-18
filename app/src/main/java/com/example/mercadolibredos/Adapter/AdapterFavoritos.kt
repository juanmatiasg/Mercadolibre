package com.example.mercadolibredos.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log

import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Activities.DescripcionActivity
import com.example.mercadolibredos.Activities.MainActivity
import com.example.mercadolibredos.Api.Api
import com.example.mercadolibredos.Modelo.Descripcion
import com.example.mercadolibredos.Modelo.Items
import com.example.mercadolibredos.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favoritos.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AdapterFavoritos : RecyclerView.Adapter<ViewHolderFavoritos>() {
    var lista: MutableList<Items> = mutableListOf()
    lateinit var context: Context

    fun AdapterFavoritos(lista: MutableList<Items>, context: Context) {
        this.lista = lista
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoritos {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderFavoritos(layoutInflater.inflate(R.layout.item_favoritos, parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolderFavoritos, position: Int) {
        holder.id.setText(lista.get(position).id)
        holder.title.setText(lista.get(position).title)
        holder.price.setText(lista.get(position).price.toString())
        holder.photos.loadUrl(lista.get(position).thumbnail)

        holder.cardView.setOnClickListener(object : View.OnClickListener {  /*Hago click en el cardView y me lleva a otra activity*/

            override fun onClick(v: View) {

                var intent = Intent(v.context, DescripcionActivity::class.java)
                intent.putExtra("Image", lista.get(position).thumbnail)
                intent.putExtra("Title", holder.title.text.toString())
                intent.putExtra("Price", holder.price.text.toString())


                /*Mando el llamo a la interfaz del API y paso por parametro el id que necesita para
                * obtener la descripcion del producto*/

                var service = Api.getRetrofit()
                service.getAllDescriptions(lista.get(position).id).enqueue(object : Callback<Descripcion> {
                    override fun onFailure(call: Call<Descripcion>, t: Throwable) {
                        Log.i(MainActivity.TAG, "No hay datos")
                    }

                    override fun onResponse(call: Call<Descripcion>, response: Response<Descripcion>) {
                        var respuesta = response.body() as Descripcion
                        var lista: ArrayList<Descripcion> = ArrayList()
                        lista.add(respuesta) /*Funciona Perfecto*/


                        intent.putExtra("Descripcion", respuesta.plain_text)
                        v.context.startActivity(intent) /*otra Activity(Descripcion)*/

                    }

                })


            }

        })


    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(imageViewFavoritos)

    }


}

