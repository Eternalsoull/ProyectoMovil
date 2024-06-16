package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.RolEntity
@Dao
interface RolDao {

    @Insert
    fun insertRol( rol: RolEntity)

    @Delete
    fun deleteRol( rol: RolEntity )

    @Update
    fun updateRol( rol: RolEntity)

    @Query("SELECT * FROM rol")
    fun getAllroles(): List<RolEntity>

    //borar todos los roles
    @Query("DELETE FROM rol")
    fun deleteAllRoles()

}
