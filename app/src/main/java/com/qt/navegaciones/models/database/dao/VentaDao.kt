package com.qt.navegaciones.models.database.dao

import androidx.room.*
import com.qt.navegaciones.models.database.entities.VentaEntity

@Dao
interface VentaDao {
    @Insert
    fun insertVenta(venta: VentaEntity): Long

    @Delete
    fun deleteVenta(venta: VentaEntity)

    @Update
    fun updateVenta(venta: VentaEntity)

    @Query("SELECT * FROM ventas")
    fun getAllVentas(): List<VentaEntity>

    @Query("SELECT * FROM ventas WHERE id_Venta = :idVenta")
    fun getVentaById(idVenta: Int): VentaEntity

}
