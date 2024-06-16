package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.ProductoEntity

class Productos {
    var productos = ArrayList<ProductoEntity>()

    constructor(productos: ArrayList<ProductoEntity>){
        this.productos = productos
    }
}