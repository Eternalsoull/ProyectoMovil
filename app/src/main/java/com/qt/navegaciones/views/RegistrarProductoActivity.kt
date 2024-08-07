package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.qt.navegaciones.PantallaBienvenida
import com.qt.navegaciones.R
import com.qt.navegaciones.databinding.ActivityRegistrarProductoBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.ProductoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarProductoActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityRegistrarProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistrar.setOnClickListener(this)
        binding.btnVolver.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                registrarProducto()
            }
            R.id.btnVolver -> {
                val intent = Intent(this, PantallaBienvenida::class.java)
                startActivity(intent)
            }
        }
    }

    private fun registrarProducto() {
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etPrecio.text.toString().toDoubleOrNull()
        val cantidad = binding.etCantidad.text.toString().toIntOrNull()

        if (nombre.isEmpty() || precio == null) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        val productoEntity = ProductoEntity()
        productoEntity.Nombre = nombre
        productoEntity.Precio = precio
        productoEntity.Cantidad = cantidad ?: 0



        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Globals.getdataBase(this@RegistrarProductoActivity)?.productoDao()?.insertProducto(productoEntity)
            }
            Toast.makeText(this@RegistrarProductoActivity, "Se ha agregado un producto", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
