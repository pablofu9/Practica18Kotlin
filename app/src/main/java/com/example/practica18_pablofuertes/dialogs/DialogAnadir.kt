package com.example.practica18_pablofuertes.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
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
    private lateinit var t5:Spinner
    private lateinit var t6:Spinner
    private lateinit var t7:EditText
    private lateinit var contador:TextView
    private var onDismissListener: DialogInterface.OnDismissListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder=AlertDialog.Builder(context)
        val inflater=requireActivity().layoutInflater
        val view: View =inflater.inflate(R.layout.ventana_anadir, null)
        //Campos de texto y spinner
        t2=view.findViewById(R.id.t2)
        t3=view.findViewById(R.id.t3)
        t4=view.findViewById(R.id.t4)
        t5=view.findViewById(R.id.t5)
        t6=view.findViewById(R.id.t6)
        t7=view.findViewById(R.id.t7)
        contador=view.findViewById(R.id.contador)
        contador.text="17"
        //Cremamos los arrays y los adaptadores para los spinner de colores y combustible
        val arrayColores = arrayOf("Blanco","Negro","Azul","Rojo","Gris","Verde","Amarillo")
        val arrayCombustible = arrayOf("Gasolina","Diesel","Hibrido")
        val spinnerCombustibleAdapter = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayCombustible) }
        val spinnerColoresAdapter = context?.let { ArrayAdapter(it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayColores) }
        t5.adapter=spinnerCombustibleAdapter
        t6.adapter=spinnerColoresAdapter


        //Vamos a hacer un contador para que el bastidor solo se puedan introducir 17 numeros
        t2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val max:Int=17
                val caracteresRestantes:Int=max-t2.length()
                contador.setText(caracteresRestantes.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        //Vista
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
                //Boton de aceptar, si hay algun campo vacio no se inserta el vehiculo
                if(t2.text.isEmpty() || t3.text.isEmpty() || t4.text.isEmpty() ||  t7.text.isEmpty()) {
                    Toast.makeText(context, "Hay algun campo vacio", Toast.LENGTH_LONG).show()
                }else if(t2.text.length !=17) {
                    Toast.makeText(context, "Numero de bastidor incorrecto", Toast.LENGTH_LONG).show()
                }else if(t7.text.toString().toInt() > 500000){
                    Toast.makeText(context, "Numero de km por encima del maximo", Toast.LENGTH_LONG).show()
                }else{
                    //Si todos los campos estan llenos el vehiculo se insertara
                    val v:Vehiculo=
                        Vehiculo(t2.text.toString(),t3.text.toString(),t4.text.toString(),t5.selectedItem.toString(),t6.selectedItem.toString(),t7.text.toString().toInt())
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