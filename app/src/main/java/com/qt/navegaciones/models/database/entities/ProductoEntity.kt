package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "producto")
class ProductoEntity {
    @PrimaryKey
    var id_Producto: Int = 0
    var nombre: String = ""
    var precio: Double = 0.0
}