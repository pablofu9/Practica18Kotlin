package com.example.practica18_pablofuertes.activities

import android.R.layout.simple_list_item_1
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.ListView
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.controller.VehiculoAdapter

class Ver : AppCompatActivity() {
    private lateinit var lista:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver)

        lista=findViewById(R.id.lista)
        val helper=SqliteHelper(this)
        val cursor: Cursor =helper.leerVehiculo()

        val adapter = VehiculoAdapter(this, cursor,0)
        lista.adapter=adapter
    }
}