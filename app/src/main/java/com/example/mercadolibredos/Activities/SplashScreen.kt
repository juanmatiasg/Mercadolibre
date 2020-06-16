package com.example.mercadolibredos.Activities


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mercadolibredos.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            // Este método se ejecutará una vez que termine el temporizador
            // Inicia la actividad principal de tu aplicación

            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish() /*Cierra la actividad*/
        },5000) /*Tiempo de Espera*/


    }
}
