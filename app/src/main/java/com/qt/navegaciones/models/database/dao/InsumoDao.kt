package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.InsumoEntity
@Dao
interface InsumoDao {
    @Insert
    fun insertInsumo( insumo: InsumoEntity)

    @Delete
    fun deleteInsumo( insumo: InsumoEntity )

    @Update
    fun updateInsumo( insumo: InsumoEntity )

    @Query("SELECT * FROM insumo")
    fun getAllinsumos(): List<InsumoEntity>
}



