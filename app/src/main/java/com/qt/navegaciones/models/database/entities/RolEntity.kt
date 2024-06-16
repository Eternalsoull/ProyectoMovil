package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rol")
class RolEntity {
    @PrimaryKey(autoGenerate = true)
    var id_Rol: Int = 0
    var nombre_Rol: String = ""
}