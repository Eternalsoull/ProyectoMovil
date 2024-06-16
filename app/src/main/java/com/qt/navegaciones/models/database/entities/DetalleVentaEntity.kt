package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalle_venta")
class DetalleVentaEntity {
    @PrimaryKey
    var id_Detalle_Venta: Int = 0
    var cantidad: Int = 0
    var precio: Double = 0.0
    var id_Venta: Int = 0
    var id_Producto: Int = 0
}