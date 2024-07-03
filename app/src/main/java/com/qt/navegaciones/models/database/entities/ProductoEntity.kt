package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
class ProductoEntity {
    @PrimaryKey(autoGenerate = true)
    var id_Producto: Int = 0
    var Nombre: String = ""
    var Precio: Double = 0.0
    var Cantidad: Int = 0

    // Constructor vacío
    constructor()

    // Constructor con parámetros
    constructor(idProducto: Int, nombre: String, precio: Double, cantidad: Int) {
        this.id_Producto = idProducto
        this.Nombre = nombre
        this.Precio = precio
        this.Cantidad = cantidad
    }
}
