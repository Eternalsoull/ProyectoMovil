package com.qt.navegaciones.models

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.qt.navegaciones.models.database.ProjectDatabase
import com.qt.navegaciones.models.database.entities.CarEntity
import com.qt.navegaciones.models.database.entities.ClienteEntity
import com.qt.navegaciones.models.database.entities.PersonEntity
import com.qt.navegaciones.models.database.entities.RolEntity
import com.qt.navegaciones.models.database.entities.UsuarioEntity
import com.qt.navegaciones.models.database.entities.InsumoEntity
import com.qt.navegaciones.models.database.entities.ProductoEntity
import com.qt.navegaciones.models.database.entities.VentaEntity
import com.qt.navegaciones.models.database.entities.DetalleVentaEntity
import com.qt.navegaciones.models.database.entities.VehiculoEntity

class Globals {
    companion object {
        var listaCarros :Cars = Cars(ArrayList<CarEntity>())
        var listaPersonas :Persons = Persons(ArrayList<PersonEntity>())
        var listaUsuarios :Usuarios = Usuarios(ArrayList<UsuarioEntity>())
        var listaRoles :Roles = Roles(ArrayList<RolEntity>())
        var listaClientes :Clientes = Clientes(ArrayList<ClienteEntity>())
        var listaInsumos :Insumos = Insumos(ArrayList<InsumoEntity>())
        var listaProductos :Productos = Productos(ArrayList<ProductoEntity>())
        var listaVentas :Ventas = Ventas(ArrayList<VentaEntity>())
        var listaDetallesVentas :DetallesVentas = DetallesVentas(ArrayList<DetalleVentaEntity>())
        var listaVehiculos :Vehiculos = Vehiculos(ArrayList<VehiculoEntity>())

        var dataBase : ProjectDatabase ?= null


        fun addSharedPreference(context: Context, chapter: String, key: String, value: String ){
            val sharedPreference = context.getSharedPreferences(chapter, AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getSharedPreference(context: Context,chapter:String, key: String): String? {
            val sharedPreference = context.getSharedPreferences(chapter, AppCompatActivity.MODE_PRIVATE)
            return sharedPreference.getString(key, "")
        }

        fun getdataBase(context : Context): ProjectDatabase? {
            if (dataBase == null) {
               initDatabase(context)
            }
            return dataBase
        }

        fun initDatabase(context: Context){
            dataBase =  Room.databaseBuilder(context, ProjectDatabase::class.java, "project_database").fallbackToDestructiveMigration().allowMainThreadQueries().build() //fall
        }


    }
}

//todo est