package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.VehiculoEntity

@Dao
interface VehiculoDao {
    @Insert
    fun insertVehiculo( vehiculo: VehiculoEntity)

    @Delete
    fun deleteVehiculo( vehiculo: VehiculoEntity)

    @Update
    fun updateVehiculo( vehiculo: VehiculoEntity)

    @Query("SELECT * FROM vehiculo")
    fun getAllvehiculos(): List<VehiculoEntity>

}