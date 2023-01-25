package com.example.practica18_pablofuertes.activities

import android.content.Intent
import android.content.res.Configuration
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practica18_pablofuertes.R

import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.dialogs.DialogAnadir
import com.example.practica18_pablofuertes.fragments.Fragment_Detalles
import com.example.practica18_pablofuertes.fragments.Fragment_lista
import com.example.practica18_pablofuertes.interfaces.OnFragmentEventListener
import com.example.practica18_pablofuertes.model.Vehiculo
import com.example.practica18_pablofuertes.model.VehiculoContract
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), OnFragmentEventListener, View.OnClickListener {

    val fragment1 = Fragment_lista()
    val fragment2 = Fragment_Detalles()
    val manager = supportFragmentManager
    val anadir = DialogAnadir()
    private lateinit var floatingButton:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.practica18_pablofuertes.R.layout.activity_main)
        floatingButton=findViewById(R.id.floatingButton)
        floatingButton.setOnClickListener(this)
        //Cargamos el fragment del listado de vehiculos
        onResume()



    }

    override fun onResume() {
        super.onResume()
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame1, fragment1)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onFragmentEvent(cursor: Cursor) {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(VehiculoContract.ID))
        val numeroBastidor = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.BASTIDOR))
        val marca = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MARCA))
        val modelo = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MODELO))
        val combustible = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.COMBUSTIBLE))
        val color = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.COLOR))
        val km = cursor.getInt(cursor.getColumnIndexOrThrow(VehiculoContract.KM))

        val v:Vehiculo= Vehiculo(id,numeroBastidor,marca,modelo,combustible,color,km)


        //Si la orientacion es horizontal, lo que hacemos es, enviar en argumentos el vehiculo al otro fragment y desde el otro fragment creamos la vista.
        //Si el otro fragment ya esta cargado, lo borraremos y lo volvemos a crear
        val orientation = resources.configuration.orientation
        if(orientation==Configuration.ORIENTATION_LANDSCAPE){
            val bundle = Bundle()
            val transaction = manager.beginTransaction()
            bundle.putSerializable("vehiculo", v)
            fragment2.arguments=bundle
            transaction.replace(R.id.frame2, fragment2)
            transaction.addToBackStack(null)
            transaction.commit()
            if(fragment2.isAdded){
                val fragmentTransaction = manager.beginTransaction()
                fragmentTransaction.remove(fragment2)
                fragmentTransaction.commit()
                val bundle = Bundle()
                val transaction = manager.beginTransaction()
                bundle.putSerializable("vehiculo", v)
                fragment2.arguments=bundle
                transaction.replace(R.id.frame2, fragment2)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }else{

        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.floatingButton->{
                anadir.show(supportFragmentManager, "anadir")

            }
        }
    }




}