package com.example.practica18_pablofuertes.model

import android.provider.BaseColumns

object VehiculoContract:BaseColumns {
    const val TABLE_NAME="vehiculo"
    const val ID="_id"
    const val BASTIDOR="numeroBastidor"
    const val MARCA="marca"
    const val MODELO="modelo"
    const val COMBUSTIBLE="combustible"
    const val COLOR="color"
    const val KM="km"
}