package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.InsumoEntity

class Insumos {
    var insumos = ArrayList<InsumoEntity>()

    constructor(insumos: ArrayList<InsumoEntity>){
        this.insumos = insumos
    }
}