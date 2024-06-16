package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.VehiculoEntity

class Vehiculos {
    var vehiculos = ArrayList<VehiculoEntity>()

    constructor(vehiculos: ArrayList<VehiculoEntity>){
        this.vehiculos = vehiculos
    }
}