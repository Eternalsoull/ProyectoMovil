package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.DetalleVentaEntity

class DetallesVentas {
    var detallesVentas = ArrayList<DetalleVentaEntity>()

    constructor(detallesVentas: ArrayList<DetalleVentaEntity>){
        this.detallesVentas = detallesVentas
    }
}