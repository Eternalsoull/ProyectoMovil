package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.qt.navegaciones.PantallaBienvenida
import com.qt.navegaciones.R
import com.qt.navegaciones.databinding.ActivityRegistrarVehiculoBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.ProductoEntity
import com.qt.navegaciones.models.database.entities.VehiculoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarVehiculoActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityRegistrarVehiculoBinding
    private var productos: List<ProductoEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistrar.setOnClickListener(this)
        binding.btnVolver.setOnClickListener(this)

        // Load products and populate spinner
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                productos = Globals.getdataBase(this@RegistrarVehiculoActivity)?.productoDao()?.getAllproductos() ?: emptyList()
            }
            val adapter = ArrayAdapter(this@RegistrarVehiculoActivity, android.R.layout.simple_spinner_item, productos.map { it.Nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerProducto.adapter = adapter
            binding.spinnerProducto.onItemSelectedListener = this@RegistrarVehiculoActivity
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                registrarVehiculo()
            }
            R.id.btnVolver -> {
                val intent = Intent(this, PantallaBienvenida::class.java)
                startActivity(intent)
            }
        }
    }

    private fun registrarVehiculo() {
        val marca = binding.etMarca.text.toString()
        val modelo = binding.etModelo.text.toString()
        val color = binding.etColor.text.toString()
        val placa = binding.etPlaca.text.toString()
        val selectedProducto = binding.spinnerProducto.selectedItemPosition

        if (marca.isEmpty() || modelo.isEmpty() || color.isEmpty() || placa.isEmpty() || selectedProducto < 0) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        val vehiculoEntity = VehiculoEntity().apply {
            Marca = marca
            Modelo = modelo
            Color = color
            Placa = placa
            id_Producto = productos[selectedProducto].id_Producto
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Globals.getdataBase(this@RegistrarVehiculoActivity)?.vehiculoDao()?.insertVehiculo(vehiculoEntity)
            }
            Toast.makeText(this@RegistrarVehiculoActivity, "Se ha agregado un vehículo", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
}
