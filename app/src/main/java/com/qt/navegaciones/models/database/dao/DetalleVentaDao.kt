package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.DetalleVentaEntity
@Dao
interface DetalleVentaDao {
    @Insert
    fun insertDetalleVenta( detalleventa: DetalleVentaEntity)

    @Delete
    fun deleteDetalleVenta( detalleventa: DetalleVentaEntity )

    @Update
    fun updateDetalleVenta( detalleventa: DetalleVentaEntity )

    @Query("SELECT * FROM detalle_venta")
    fun getAlldetalleventas(): List<DetalleVentaEntity>
}

