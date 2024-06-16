package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.ClienteEntity

class Clientes {
    var clientes = ArrayList<ClienteEntity>()

    constructor(clientes: ArrayList<ClienteEntity>){
        this.clientes = clientes
    }
}