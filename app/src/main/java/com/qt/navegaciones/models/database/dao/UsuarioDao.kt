package com.qt.navegaciones.models.database.dao
import androidx.room.*
import com.qt.navegaciones.models.database.entities.UsuarioEntity
@Dao
interface UsuarioDao {


        @Insert
        fun insertUsuario( usuario: UsuarioEntity)

        @Delete
        fun deleteUsuario( usuario: UsuarioEntity)

        @Update
        fun updateUsuario( usuario: UsuarioEntity)

        @Query("SELECT * FROM usuario")
        fun getAllusuarios(): List<UsuarioEntity>

        @Query("SELECT * FROM usuario WHERE cedula = :cedula AND contrasena = :contrasena")
        fun getUsuarioByCedulaContrasena(cedula: String, contrasena: String): UsuarioEntity

        //obtener un usuario por cedula
        @Query("SELECT * FROM usuario WHERE cedula = :cedula")
        fun getUsuarioByCedula(cedula: String): UsuarioEntity

        //obtener un usuario por nombre
        @Query("SELECT * FROM usuario WHERE nombre = :nombre")
        fun getUsuarioByNombre(nombre: String): UsuarioEntity



}