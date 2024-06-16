package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "venta")
class VentaEntity {
    @PrimaryKey
    var id_Venta: Int = 0
    var fecha: Long = 0
    var monto_Total: Double = 0.0
    var id_Usuario: Int = 0
}