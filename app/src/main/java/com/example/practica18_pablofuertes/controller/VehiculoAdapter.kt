package com.example.practica18_pablofuertes.controller

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.practica18_pablofuertes.R
import com.example.practica18_pablofuertes.model.VehiculoContract
import com.google.android.material.bottomsheet.BottomSheetBehavior.SaveFlags

class VehiculoAdapter(context:Context, c:Cursor, flags:Int): CursorAdapter(context,c,flags) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val convertView = inflater.inflate(R.layout.vehiculo_item, null)
        return convertView
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val txtBastidor=view?.findViewById<TextView>(R.id.txtBastidor)
        val txtMarca=view?.findViewById<TextView>(R.id.txtMarca)
        val txtModelo=view?.findViewById<TextView>(R.id.txtModelo)

        txtBastidor?.text= cursor?.getString(cursor.getColumnIndexOrThrow(VehiculoContract.BASTIDOR))
        txtMarca?.text= cursor?.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MARCA))
        txtModelo?.text= cursor?.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MODELO))

    }
}