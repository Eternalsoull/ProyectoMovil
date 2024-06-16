package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "usuario",
    foreignKeys = [ForeignKey(
        entity = RolEntity::class,
        parentColumns = ["id_Rol"],
        childColumns = ["id_Rol"],
        onDelete = ForeignKey.CASCADE
    )]
)
class UsuarioEntity {
    @PrimaryKey(autoGenerate = true)
    var id_Usuario: Int = 0
    var cedula: String = ""
    var nombre: String = ""
    var contrasena: String = ""
    var id_Rol: Int = 0

    fun getFullName(): String {
        return "$nombre "
    }
}