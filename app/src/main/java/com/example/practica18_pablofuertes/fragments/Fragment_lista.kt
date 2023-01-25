package com.example.practica18_pablofuertes.fragments

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.controller.VehiculoAdapter


class Fragment_lista : Fragment() {

    private lateinit var listaFragment:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        listaFragment=view.findViewById(R.id.listaFragment)
        val helper = SqliteHelper(context)
        val cursor:Cursor=helper.leerVehiculo()
        val adapter = context?.let { VehiculoAdapter(it.applicationContext, cursor,0) }
        listaFragment.adapter=adapter
        return view

    }


    }
