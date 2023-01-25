package com.example.practica18_pablofuertes.fragments

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.controller.SqliteHelper
import com.example.practica18_pablofuertes.controller.VehiculoAdapter
import com.example.practica18_pablofuertes.interfaces.OnFragmentEventListener
import com.example.practica18_pablofuertes.model.Vehiculo
import com.example.practica18_pablofuertes.model.VehiculoContract


class Fragment_lista : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var adapter: VehiculoAdapter
    private lateinit var listaFragment:ListView
    private var listener : OnFragmentEventListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        listaFragment=view.findViewById(R.id.listaFragment)
        refresh()
        val orientation = resources.configuration.orientation
        if(orientation!= android.content.res.Configuration.ORIENTATION_LANDSCAPE){
            registerForContextMenu(listaFragment)

        }

        listaFragment.onItemClickListener = this
        return view




    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentEventListener){
            listener=context
        }
    }
    override fun onDetach() {
        super.onDetach()
        //Libera la referencia al listener
        listener=null
    }

    override fun onItemClick(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listener?.let {
            // Le pasamos un cursor a la actividad
            val cursor: Cursor = parent?.getItemAtPosition(position) as Cursor
            // Se lo pasa a la actividad
            listener!!.onFragmentEvent(cursor)

            //Desde el main vamos a construir el vehiculo y se lo vamos a pasar al otro fragment
        }
    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info=item.menuInfo as AdapterContextMenuInfo
        when(item.itemId){
            R.id.menuEliminar->{

            }
            R.id.menuModificar->{

            }
        }
        return super.onContextItemSelected(item)
    }
    fun refresh(){
        val helper = SqliteHelper(context)
        val cursor:Cursor=helper.leerVehiculo()
        adapter = context?.let { VehiculoAdapter(it.applicationContext, cursor,0) }!!
        listaFragment.adapter=adapter

    }
}
