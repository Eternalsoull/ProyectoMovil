package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "insumo")
class InsumoEntity {
    @PrimaryKey
    var id_Insumo: Int = 0
    var descripcion: String = ""
    var id_Producto: Int = 0

}