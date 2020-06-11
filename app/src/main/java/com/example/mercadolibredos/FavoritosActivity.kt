package com.example.mercadolibredos


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibredos.Adapter.ProductosAdapter
import kotlinx.android.synthetic.main.activity_favoritos.*
import kotlinx.android.synthetic.main.item_productos.*

class FavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        /*var bundle: Bundle? = intent.extras
         */
        var string = intent.getSerializableExtra("id") /*Funciona*/
        idFavoritos.setText(string.toString())

    }


}
