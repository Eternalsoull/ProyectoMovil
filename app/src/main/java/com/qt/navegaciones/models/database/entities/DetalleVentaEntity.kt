package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Detalle_Venta",
    foreignKeys = [
        ForeignKey(
            entity = VentaEntity::class,
            parentColumns = ["id_Venta"],
            childColumns = ["id_Venta"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductoEntity::class,
            parentColumns = ["id_Producto"],
            childColumns = ["id_Producto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class DetalleVentaEntity {
    @PrimaryKey
    var id_Detalle_Venta: Int = 0
    var Cantidad: Int = 0
    var Precio: Double = 0.0
    var id_Venta: Int = 0
    var id_Producto: Int = 0
}
