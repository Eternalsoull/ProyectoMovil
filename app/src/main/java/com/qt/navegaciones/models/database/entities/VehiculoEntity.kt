package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Vehiculo",
    foreignKeys = [ForeignKey(
        entity = ProductoEntity::class,
        parentColumns = ["id_Producto"],
        childColumns = ["id_Producto"],
        onDelete = ForeignKey.CASCADE
    )]
)
class VehiculoEntity {
    @PrimaryKey
    var id_Vehiculo: Int = 0
    var Marca: String = ""
    var Modelo: String = ""
    var color: String = ""
    var Placa: String = ""
    var id_Producto: Int = 0
}
