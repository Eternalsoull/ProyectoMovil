package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
class ProductoEntity {
    @PrimaryKey
    var id_Producto: Int = 0
    var Nombre: String = ""
    var Precio: Double = 0.0
}
