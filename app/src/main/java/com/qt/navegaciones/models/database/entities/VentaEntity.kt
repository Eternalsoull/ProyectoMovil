package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ventas")
data class VentaEntity(
    @PrimaryKey(autoGenerate = true) val id_Venta: Int = 0,
    val id_Producto: Int,
    val Nombre: String,
    val Precio: Double,
    val Cantidad: Int,
    val PrecioTotal: Double,
    val Fecha: Date, // Nuevo campo para la fecha de la venta
) {
}
