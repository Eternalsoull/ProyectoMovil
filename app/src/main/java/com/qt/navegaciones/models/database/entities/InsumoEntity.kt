package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Insumo",
    foreignKeys = [ForeignKey(
        entity = ProductoEntity::class,
        parentColumns = ["id_Producto"],
        childColumns = ["id_Producto"],
        onDelete = ForeignKey.CASCADE
    )]
)
class InsumoEntity {
    @PrimaryKey(autoGenerate = true)
    var id_Insumo: Int = 0
    var Descripcion: String = ""
    var id_Producto: Int = 0
}
