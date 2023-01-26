package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.model.Vehiculo

class DialogModificar(private val cursor:Cursor):DialogFragment(),DialogInterface.OnClickListener {

    private lateinit var t2: EditText
    private lateinit var t3: EditText
    private lateinit var t4: EditText
    private lateinit var t5: EditText
    private lateinit var t6: EditText
    private lateinit var t7: EditText
    private var onDismissListener: DialogInterface.OnDismissListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(context)
        val inflater=requireActivity().layoutInflater
        val view: View =inflater.inflate(R.layout.ventana_anadir, null)
        t2=view.findViewById(R.id.t2)
        t3=view.findViewById(R.id.t3)
        t4=view.findViewById(R.id.t4)
        t5=view.findViewById(R.id.t5)
        t6=view.findViewById(R.id.t6)
        t7=view.findViewById(R.id.t7)
        builder.setView(view)
        builder.setTitle("Modificar vehiculo")
        builder.setMessage("Modifica el vehiculo")
        builder.setPositiveButton("Modificar", this)
        builder.setNegativeButton("Salir",this)

        val helper=SqliteHelper(context)
        val v1:Vehiculo=helper.devuelveVehiculo(cursor)
        //Seteamos los campos de texto
        t2.setText(v1.numeroBastidor)
        t3.setText(v1.marca)
        t4.setText(v1.modelo)
        t5.setText(v1.combustible)
        t6.setText(v1.color)
        t7.setText(v1.km.toString())

        t2.isEnabled=false
        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when(which){
            -1->{
               //Boton de aceptar
                val helper =SqliteHelper(context)

            }
            -2->{
                //No hace nada, solo sale hacia atras
            }
        }
    }
}