package com.example.practica18_pablofuertes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper


class Fragment_lista : Fragment() {

    private lateinit var listaFragment:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        listaFragment=view.findViewById(R.id.listaFragment)

        return view

    }


    }
