package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Venta",
    foreignKeys = [ForeignKey(
        entity = UsuarioEntity::class,
        parentColumns = ["id_Usuario"],
        childColumns = ["id_Usuario"],
        onDelete = ForeignKey.CASCADE
    )]
)
class VentaEntity {
    @PrimaryKey(autoGenerate = true)
    var id_Venta: Int = 0
    var Fecha: Long = 0
    var Monto_Total: Double = 0.0
    var id_Usuario: Int = 0
}
