package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.fragments.Fragment_lista
import com.example.practica18_pablofuertes.model.Vehiculo

class DialogModificar(private val cursor:Cursor):DialogFragment(),DialogInterface.OnClickListener {

    private lateinit var t2: EditText
    private lateinit var t3: EditText
    private lateinit var t4: EditText
    private lateinit var t5: Spinner
    private lateinit var t6: Spinner
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

        //Cremamos los arrays y los adaptadores para los spinner de colores y combustible
        val arrayColores = arrayOf("Blanco","Negro","Azul","Rojo","Gris","Verde","Amarillo")
        val arrayCombustible = arrayOf("Gasolina","Diesel","Hibrido")
        val spinnerCombustibleAdapter = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayCombustible) }
        val spinnerColoresAdapter = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayColores) }
        t5.adapter=spinnerCombustibleAdapter
        t6.adapter=spinnerColoresAdapter

        builder.setView(view)
        builder.setTitle("Modificar vehiculo")
        builder.setMessage("Modifica el vehiculo")
        builder.setPositiveButton("Modificar", this)
        builder.setNegativeButton("Salir",this)

        val helper=SqliteHelper(context)
        val v1 : Vehiculo =helper.devuelveVehiculo(cursor)

        //Seteamos los campos de texto
        //Cogemos la posicion de los dos spinner para dejar la que corresponde al vehiculo que vamos a modificar
        val positionSpinnerColor = spinnerColoresAdapter?.getPosition(v1.color)
        val positionSpinnerCombustible = spinnerColoresAdapter?.getPosition(v1.combustible)
        t2.setText(v1.numeroBastidor)
        t3.setText(v1.marca)
        t4.setText(v1.modelo)
        t5.setSelection(positionSpinnerCombustible!!)
        t6.setSelection(positionSpinnerColor!!)
        t7.setText(v1.km.toString())

        t2.isEnabled=false

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when(which){
            -1->{
               //Boton de aceptar
                //usando el metodo del helper de modificar, cambiaremos el vehiculo. El bastidor no se puede tocar porque el campo esta disabled
                val helper=SqliteHelper(context)
                val v2:Vehiculo= Vehiculo(t2.text.toString(),t3.text.toString(),t4.text.toString(),t5.selectedItem.toString(),t6.selectedItem.toString(),t7.text.toString().toInt())
                helper.modificarVehiculo(v2)
                Toast.makeText(context, "Vehiculo modificado", Toast.LENGTH_LONG).show()
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