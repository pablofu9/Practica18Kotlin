package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogEliminar(any: Any) : DialogFragment(), DialogInterface.OnClickListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(context)
        builder.setTitle("Eliminar vehiculo")
        builder.setMessage("Seguro que quieres eliminar")
        builder.setPositiveButton("Eliminar", this)
        builder.setNegativeButton("Cancelar",this)
        return builder.create()

    }
    override fun onClick(p0: DialogInterface?, p1: Int) {
        when(p1){
            -1->{

                //Boton de eliminar
                /*
                val i = arguments?.getString("vehiculo")
                println(i)*/
            }
            -2->{
                //No hace nada, solo sale hacia atras (Boton de cancelar)
            }
        }
    }
}