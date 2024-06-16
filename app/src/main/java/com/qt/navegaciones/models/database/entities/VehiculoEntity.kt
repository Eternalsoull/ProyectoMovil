package com.qt.navegaciones.models.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculo")
class VehiculoEntity {
    @PrimaryKey
    var id_Vehiculo: Int = 0
    var marca: String = ""
    var modelo: String = ""
    var color: String = ""
    var placa: String = ""
    var id_Producto: Int = 0

}