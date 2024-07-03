package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "detalle_venta",
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
data class DetalleVentaEntity(
    @PrimaryKey(autoGenerate = true)
    var id_DetalleVenta: Int = 0,
    var id_Venta: Int,
    var id_Producto: Int,
    var cantidad: Int,
    var precio: Double // Save the price at the time of sale
)
