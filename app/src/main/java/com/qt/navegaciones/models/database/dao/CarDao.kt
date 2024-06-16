package com.qt.navegaciones.models.database.dao

import androidx.room.*
import com.qt.navegaciones.models.database.entities.CarEntity

@Dao
interface CarDao {
    @Insert
    fun insertCar( car: CarEntity)

    @Delete
    fun deleteCar( car: CarEntity)

    @Update
    fun updateCar( car: CarEntity)

    @Query("SELECT * FROM car")
    fun getAllCars(): List<CarEntity>
}