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
            "INSERT INTO " +VehiculoContract.TABLE_NAME + " (_id,numeroBastidor,marca,modelo,combustible,color,km) VALUES" +
                    "(1, '1GT01ZE81B8481459', 'Audi', 'A3', 'Gasolina', 'Negro', 60000)," +
                    "(2, '3GCPKTD35B6413373', 'Mercedes', 'Clase A', 'Gasolina', 'Azul', 2000)," +
                    "(3, 'WVWBM9AJ5B6153742', 'BMW', 'Serie 3', 'Diesel', 'Verde', 120000)," +
                    "(4, 'WAUDGBFL4B7122927', 'Seat', 'Leon', 'Gasolina', 'Negro', 100);"

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
        values.put(VehiculoContract.ID, vehiculo._id)
        values.put(VehiculoContract.BASTIDOR,vehiculo.numeroBastidor)
        values.put(VehiculoContract.MARCA, vehiculo.marca)
        values.put(VehiculoContract.MODELO, vehiculo.modelo)
        values.put(VehiculoContract.COMBUSTIBLE, vehiculo.combustible)
        values.put(VehiculoContract.COLOR, vehiculo.color)
        values.put(VehiculoContract.KM, vehiculo.km)

        return db.insert(VehiculoContract.TABLE_NAME,null, values)

    }


}