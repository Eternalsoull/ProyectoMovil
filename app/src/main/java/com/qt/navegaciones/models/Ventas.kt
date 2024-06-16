package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.VentaEntity

class Ventas {
    var ventas = ArrayList<VentaEntity>()

    constructor(ventas: ArrayList<VentaEntity>){
        this.ventas = ventas
    }
}