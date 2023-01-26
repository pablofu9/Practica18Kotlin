package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.fragments.Fragment_lista
import com.example.practica18_pablofuertes.model.Vehiculo

class DialogAnadir():DialogFragment(), DialogInterface.OnClickListener {
    private lateinit var t2:EditText
    private lateinit var t3:EditText
    private lateinit var t4:EditText
    private lateinit var t5:EditText
    private lateinit var t6:EditText
    private lateinit var t7:EditText
    private var onDismissListener: DialogInterface.OnDismissListener? = null



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder=AlertDialog.Builder(context)
        val inflater=requireActivity().layoutInflater
        val view: View =inflater.inflate(R.layout.ventana_anadir, null)
        t2=view.findViewById(R.id.t2)
        t3=view.findViewById(R.id.t3)
        t4=view.findViewById(R.id.t4)
        t5=view.findViewById(R.id.t5)
        t6=view.findViewById(R.id.t6)
        t7=view.findViewById(R.id.t7)
        builder.setView(view)
        builder.setTitle("Anadir vehiculo")
        builder.setMessage("Añade un vehiculo nuevo")
        builder.setPositiveButton("Añadir", this)
        builder.setNegativeButton("Cancelar",this)
        return builder.create()
    }
    override fun onClick(p0: DialogInterface?, p1: Int) {

        when(p1){
            -1->{
                if(t2.text.isEmpty() || t3.text.isEmpty() || t4.text.isEmpty() || t5.text.isEmpty() || t6.text.isEmpty() || t7.text.isEmpty()){
                    Toast.makeText(context,"Hay algun campo vacio", Toast.LENGTH_LONG).show()

                }else{
                    val v:Vehiculo=
                        Vehiculo(t2.text.toString(),t3.text.toString(),t4.text.toString(),t5.text.toString(),t6.text.toString(),t7.text.toString().toInt())
                    val helper=SqliteHelper(context)
                    helper.insertarVehiculo(v)
                    Toast.makeText(context,"Vehiculo insertado", Toast.LENGTH_LONG).show()
                }
            }
            -2->{
                //No hace nada, solo sale hacia atras
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