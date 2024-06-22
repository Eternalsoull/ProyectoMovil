package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
@Entity(
    tableName = "Cliente",
    foreignKeys = [ForeignKey(
        entity = UsuarioEntity::class,
        parentColumns = ["id_Usuario"],
        childColumns = ["id_Usuario"],
        onDelete = ForeignKey.CASCADE
    )]
)
class ClienteEntity {
    @PrimaryKey
    var id_Cliente: Int = 0
    var Cedula: String = ""
    var Nombre: String = ""
    var Direccion: String = ""
    var Telefono: String = ""
    var id_Usuario: Int = 0
}