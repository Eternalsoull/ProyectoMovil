package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")

class CarEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var marca: String = ""
    var modelo: String = ""
    var color: String = ""
    var placa: String = ""
    var url: String = ""

    fun getFullName(): String {
        return "$marca $modelo"
    }
}