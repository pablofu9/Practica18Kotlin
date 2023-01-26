package com.example.practica18_pablofuertes.controller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practica18_pablofuertes.model.Vehiculo
import com.example.practica18_pablofuertes.model.VehiculoContract

class SqliteHelper(context:Context?):SQLiteOpenHelper(context,NAME,null,VERSION) {

    companion object{
        private const val NAME="vehiculos.db"
        private const val VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE "+
                    VehiculoContract.TABLE_NAME + " ("
                    +VehiculoContract.ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                    +VehiculoContract.BASTIDOR + " TEXT UNIQUE NOT NULL, "
                    +VehiculoContract.MARCA + " TEXT, "
                    +VehiculoContract.MODELO + " TEXT, "
                    +VehiculoContract.COMBUSTIBLE + " TEXT, "
                    +VehiculoContract.COLOR + " TEXT, "
                    +VehiculoContract.KM + " INTEGER );"
        )
        db?.execSQL(
            "INSERT INTO " +VehiculoContract.TABLE_NAME + " (numeroBastidor,marca,modelo,combustible,color,km) VALUES" +
                    "('1GT01ZE81B8481459', 'Audi', 'A3', 'Gasolina', 'Negro', 60000)," +
                    "('3GCPKTD35B6413373', 'Mercedes', 'Clase A', 'Gasolina', 'Azul', 2000)," +
                    "('WVWBM9AJ5B6153742', 'BMW', 'Serie 3', 'Diesel', 'Verde', 120000)," +
                    "('WAUDGBFL4B7122927', 'Seat', 'Leon', 'Gasolina', 'Negro', 100);"

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    //Metodo para sacar los vehiculos
    fun leerVehiculo(): Cursor {
        val db = readableDatabase
        val sql = "select * from "+VehiculoContract.TABLE_NAME
        return db.rawQuery(sql,null)
    }

    //Metodo para insertar un vehiculo nuevo
    fun insertarVehiculo(vehiculo:Vehiculo): Long{
        val db = writableDatabase
        val values = ContentValues()
        values.put(VehiculoContract.BASTIDOR,vehiculo.numeroBastidor)
        values.put(VehiculoContract.MARCA, vehiculo.marca)
        values.put(VehiculoContract.MODELO, vehiculo.modelo)
        values.put(VehiculoContract.COMBUSTIBLE, vehiculo.combustible)
        values.put(VehiculoContract.COLOR, vehiculo.color)
        values.put(VehiculoContract.KM, vehiculo.km)

        return db.insert(VehiculoContract.TABLE_NAME,null, values)

    }
    //Convertir un vehiculo desde un cursor
    fun devuelveVehiculo(cursor:Cursor): Vehiculo{
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(VehiculoContract.ID))
        val numeroBastidor = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.BASTIDOR))
        val marca = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MARCA))
        val modelo = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.MODELO))
        val combustible = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.COMBUSTIBLE))
        val color = cursor.getString(cursor.getColumnIndexOrThrow(VehiculoContract.COLOR))
        val km = cursor.getInt(cursor.getColumnIndexOrThrow(VehiculoContract.KM))

        val v:Vehiculo= Vehiculo(id,numeroBastidor,marca,modelo,combustible,color,km)
        return v
    }

    fun eliminarVehiculo(vehiculo:Vehiculo){
        val db = writableDatabase
        db.execSQL("delete from "+VehiculoContract.TABLE_NAME+" where "+VehiculoContract.BASTIDOR+" ='"+vehiculo.numeroBastidor+"'")
    }

    //Metodo update
    fun modificarVehiculo(vehiculo: Vehiculo){
        val db=writableDatabase
        val values = ContentValues()

        values.put(VehiculoContract.MARCA,vehiculo.marca)
        values.put(VehiculoContract.MODELO,vehiculo.modelo)
        values.put(VehiculoContract.COMBUSTIBLE,vehiculo.combustible)
        values.put(VehiculoContract.COLOR,vehiculo.color)
        values.put(VehiculoContract.KM,vehiculo.km)
        db.update(VehiculoContract.TABLE_NAME, values, VehiculoContract.BASTIDOR+" =?", arrayOf(vehiculo.numeroBastidor))
    }


}