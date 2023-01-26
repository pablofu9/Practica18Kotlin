package com.example.practica18_pablofuertes.model

class Vehiculo:java.io.Serializable {
    var _id:Int
    var numeroBastidor:String
    var marca:String
    var modelo:String
    var combustible:String
    var color:String
    var km:Int

    constructor(
        _id: Int,
        numeroBastidor: String,
        marca: String,
        modelo: String,
        combustible: String,
        color: String,
        km: Int
    ) {
        this._id = _id
        this.numeroBastidor = numeroBastidor
        this.marca = marca
        this.modelo = modelo
        this.combustible = combustible
        this.color = color
        this.km = km
    }

    constructor(numeroBastidor: String, marca: String, modelo: String, combustible: String, color: String, km: Int) {
        this._id=0
        this.numeroBastidor = numeroBastidor
        this.marca = marca
        this.modelo = modelo
        this.combustible = combustible
        this.color = color
        this.km = km
    }

    override fun toString(): String {
        return "Vehiculo(_id=$_id, numeroBastidor='$numeroBastidor', marca='$marca', modelo='$modelo', combustible='$combustible', color='$color', km=$km)"
    }


}