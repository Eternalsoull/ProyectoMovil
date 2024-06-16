package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cliente")
class ClienteEntity {
    @PrimaryKey
    var id_Cliente: Int = 0
    var nombre: String = ""
    var direccion: String = ""
    var telefono: String = ""
    var id_Usuario: Int = 0
}
