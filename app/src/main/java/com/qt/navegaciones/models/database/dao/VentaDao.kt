package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.VentaEntity

@Dao
interface VentaDao {
    @Insert
    fun insertVenta( venta: VentaEntity)

    @Delete
    fun deleteVenta( venta: VentaEntity)

    @Update
    fun updateVenta( venta: VentaEntity)

    @Query("SELECT * FROM venta")
    fun getAllventas(): List<VentaEntity>

}