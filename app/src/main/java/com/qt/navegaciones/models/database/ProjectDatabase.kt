package com.qt.navegaciones.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qt.navegaciones.models.database.dao.CarDao
import com.qt.navegaciones.models.database.dao.ClienteDao
import com.qt.navegaciones.models.database.dao.DetalleVentaDao
import com.qt.navegaciones.models.database.dao.InsumoDao
import com.qt.navegaciones.models.database.dao.PersonDao
import com.qt.navegaciones.models.database.dao.ProductoDao
import com.qt.navegaciones.models.database.dao.RolDao
import com.qt.navegaciones.models.database.dao.UsuarioDao
import com.qt.navegaciones.models.database.dao.VehiculoDao
import com.qt.navegaciones.models.database.dao.VentaDao
import com.qt.navegaciones.models.database.entities.CarEntity
import com.qt.navegaciones.models.database.entities.ClienteEntity
import com.qt.navegaciones.models.database.entities.DetalleVentaEntity
import com.qt.navegaciones.models.database.entities.InsumoEntity
import com.qt.navegaciones.models.database.entities.PersonEntity
import com.qt.navegaciones.models.database.entities.ProductoEntity
import com.qt.navegaciones.models.database.entities.RolEntity
import com.qt.navegaciones.models.database.entities.UsuarioEntity
import com.qt.navegaciones.models.database.entities.VehiculoEntity
import com.qt.navegaciones.models.database.entities.VentaEntity

@Database(entities = arrayOf(CarEntity::class, PersonEntity::class, ClienteEntity::class, DetalleVentaEntity::class, InsumoEntity::class, ProductoEntity::class, RolEntity::class, UsuarioEntity::class, VehiculoEntity::class, VentaEntity::class ), version = 39)
@TypeConverters(Converters::class)
abstract class ProjectDatabase : RoomDatabase(){
   abstract fun carDao(): CarDao
   abstract fun personDao(): PersonDao
   abstract fun clienteDao(): ClienteDao
   abstract fun detalleVentaDao(): DetalleVentaDao
   abstract fun insumoDao(): InsumoDao
   abstract fun productoDao(): ProductoDao
   abstract fun rolDao(): RolDao
   abstract fun usuarioDao(): UsuarioDao
   abstract fun vehiculoDao(): VehiculoDao
   abstract fun ventaDao(): VentaDao

    companion object {
         private var INSTANCE: ProjectDatabase? = null
         fun getDatabase(context: android.content.Context): ProjectDatabase {
              if (INSTANCE == null) {
                synchronized(ProjectDatabase::class) {
                     INSTANCE = androidx.room.Room.databaseBuilder(context.applicationContext, ProjectDatabase::class.java, "project_database").fallbackToDestructiveMigration().build()
                }
              }
              return INSTANCE!!
         }
    }
}

