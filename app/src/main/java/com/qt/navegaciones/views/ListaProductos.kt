package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.MainActivity
import com.qt.navegaciones.R
import com.qt.navegaciones.adapters.ProductoAdapter
import com.qt.navegaciones.databinding.ActivityListarProductosBinding
import com.qt.navegaciones.models.Productos
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.ProductoEntity

class ListaProductos : AppCompatActivity(), View.OnClickListener {
        lateinit var binding: ActivityListarProductosBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityListarProductosBinding.inflate(layoutInflater)
            setContentView(binding.root)
            var intent = intent
            //binding.btnVolver.setOnClickListener(this)

            val productos: Productos = Productos(Globals.getdataBase(this)?.productoDao()?.getAllproductos()!! as ArrayList<ProductoEntity>)
            val adapter: ProductoAdapter = ProductoAdapter(this, productos)


            binding.lstProductos.adapter = adapter
            Toast.makeText(this, Globals.listaProductos.productos.size.toString(), Toast.LENGTH_LONG).show()

            // Añadir el listener para el SearchView
            binding.sViewProducto.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return true
                }
            })
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                // Eliminar el usuario seleccionado
                R.id.btnVolver -> {


                    // Redirigir a la pantalla de bienvenida
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

