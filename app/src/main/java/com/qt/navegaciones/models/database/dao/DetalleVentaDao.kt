package com.qt.navegaciones.models.database.dao

import androidx.room.*
import com.qt.navegaciones.models.database.entities.DetalleVentaEntity

@Dao
interface DetalleVentaDao {
    @Insert
    fun insertDetalleVenta(detalleVenta: DetalleVentaEntity)

    @Delete
    fun deleteDetalleVenta(detalleVenta: DetalleVentaEntity)

    @Update
    fun updateDetalleVenta(detalleVenta: DetalleVentaEntity)

    @Query("SELECT * FROM detalle_venta")
    fun getAllDetalleVentas(): List<DetalleVentaEntity>

    @Query("SELECT * FROM detalle_venta WHERE id_Venta = :idVenta")
    fun getDetalleVentasByVentaId(idVenta: Int): List<DetalleVentaEntity>
}
