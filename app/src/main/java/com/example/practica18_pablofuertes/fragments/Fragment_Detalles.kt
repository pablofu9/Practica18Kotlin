package com.example.practica18_pablofuertes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.model.Vehiculo


class Fragment_Detalles : Fragment() {

    private lateinit var txtIdVehiculo:TextView
    private lateinit var txtBastidorVehiculo:TextView
    private lateinit var txtMarcaVehiculo:TextView
    private lateinit var txtModeloVehiculo:TextView
    private lateinit var txtCombustibleVehiculo:TextView
    private lateinit var txtColorVehiculo:TextView
    private lateinit var txtKMVehiculo:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment__detalles, container, false)

        txtIdVehiculo=view.findViewById(R.id.txtIdVehiculo)
        txtBastidorVehiculo=view.findViewById(R.id.txtBastidorVehiculo)
        txtMarcaVehiculo=view.findViewById(R.id.txtMarcaVehiculo)
        txtModeloVehiculo=view.findViewById(R.id.txtModeloVehiculo)
        txtCombustibleVehiculo=view.findViewById(R.id.txtCombustibleVehiculo)
        txtColorVehiculo=view.findViewById(R.id.txtColorVehiculo)
        txtKMVehiculo=view.findViewById(R.id.txtKMVehiculo)

        //Si ha recibido argumentos
        if(arguments!=null){
            val vehiculo=arguments?.getSerializable("vehiculo") as Vehiculo
            txtIdVehiculo.text=vehiculo._id.toString()
            txtBastidorVehiculo.text=vehiculo.numeroBastidor
            txtMarcaVehiculo.text=vehiculo.marca
            txtModeloVehiculo.text=vehiculo.modelo
            txtCombustibleVehiculo.text=vehiculo.combustible
            txtColorVehiculo.text=vehiculo.color
            txtKMVehiculo.text=vehiculo.km.toString()
        }

        return view
    }



}