package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.ClienteEntity


@Dao
interface ClienteDao {
    @Insert
    fun insertCliente( cliente: ClienteEntity)

    @Delete
    fun deleteCliente( cliente: ClienteEntity)

    @Update
    fun updateCliente( cliente: ClienteEntity)

    @Query("SELECT * FROM cliente")
    fun getAllclientes(): List<ClienteEntity>
}