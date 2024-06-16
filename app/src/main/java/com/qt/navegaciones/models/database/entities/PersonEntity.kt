package com.qt.navegaciones.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class PersonEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    var surname: String = ""
    var phone: String = ""
    var email: String = ""
    var url : String = ""

    fun getFullName(): String {
        return "$name $surname"
    }
}