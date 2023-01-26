package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.fragments.Fragment_lista
import com.example.practica18_pablofuertes.model.Vehiculo

class DialogEliminar(private val cursor: Cursor) : DialogFragment(), DialogInterface.OnClickListener {


    private var onDismissListener: DialogInterface.OnDismissListener? = null
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
                //Con el helper rescatamos el vehiculo, convirtiendolo desde el cursos que nos llega desde el fragment
                //Despues ese vehiculo convertido lo eliminamos
                val helper=SqliteHelper(context)
                val v:Vehiculo=helper.devuelveVehiculo(cursor)
                //Borramos el vehiculo
                helper.eliminarVehiculo(v)
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
    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener?) {
        this.onDismissListener = onDismissListener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        //Refrescamos el fragment, asi volvemos a cargar la info
        val fragment = parentFragmentManager.findFragmentById(R.id.frame1) as Fragment_lista
        fragment.refresh()
        onDismissListener?.onDismiss(dialog)
    }
}