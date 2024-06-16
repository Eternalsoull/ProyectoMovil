package com.qt.navegaciones.models.database.dao

import androidx.room.*
import com.qt.navegaciones.models.database.entities.PersonEntity

@Dao
interface PersonDao {
    @Insert
    fun insertPerson( person: PersonEntity)

    @Delete
    fun deletePerson( person: PersonEntity)

    @Update
    fun updatePerson( person: PersonEntity)

    @Query("SELECT * FROM person")
    fun getAllPersons(): List<PersonEntity>
}