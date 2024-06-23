package com.qt.navegaciones.views

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.qt.navegaciones.R
import com.qt.navegaciones.databinding.ActivityRegistrarInsumoBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.InsumoEntity
import com.qt.navegaciones.models.database.entities.ProductoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarInsumoActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityRegistrarInsumoBinding
    private var productos: List<ProductoEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarInsumoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistrar.setOnClickListener(this)

        // Load products and populate spinner
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                productos = Globals.getdataBase(this@RegistrarInsumoActivity)?.productoDao()?.getAllproductos() ?: emptyList()
            }
            val adapter = ArrayAdapter(this@RegistrarInsumoActivity, android.R.layout.simple_spinner_item, productos.map { it.Nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerProducto.adapter = adapter
            binding.spinnerProducto.onItemSelectedListener = this@RegistrarInsumoActivity
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                registrarInsumo()
            }
        }
    }

    private fun registrarInsumo() {
        val descripcion = binding.etDescripcion.text.toString()
        val selectedProducto = binding.spinnerProducto.selectedItemPosition

        if (descripcion.isEmpty() || selectedProducto < 0) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        val insumoEntity = InsumoEntity().apply {
            Descripcion = descripcion
            id_Producto = productos[selectedProducto].id_Producto
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Globals.getdataBase(this@RegistrarInsumoActivity)?.insumoDao()?.insertInsumo(insumoEntity)
            }
            Toast.makeText(this@RegistrarInsumoActivity, "Se ha agregado un insumo", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
}
