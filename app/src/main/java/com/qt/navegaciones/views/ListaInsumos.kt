package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.MainActivity
import com.qt.navegaciones.R
import com.qt.navegaciones.adapters.ClienteAdapter
import com.qt.navegaciones.adapters.InsumoAdapter
import com.qt.navegaciones.databinding.ActivityListarInsumosBinding
import com.qt.navegaciones.models.Insumos
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.InsumoEntity

class ListaInsumos {
    class ListaInsumos : AppCompatActivity(), View.OnClickListener {
        lateinit var binding: ActivityListarInsumosBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityListarInsumosBinding.inflate(layoutInflater)
            setContentView(binding.root)
            var intent = intent
            //binding.btnVolver.setOnClickListener(this)

            val insumos: Insumos = Insumos(
                Globals.getdataBase(this)?.insumoDao()?.getAllinsumos()!! as ArrayList<InsumoEntity>
            )
            val adapter: InsumoAdapter = InsumoAdapter(this, insumos)


            binding.lstInsumos.adapter = adapter
            Toast.makeText(this, Globals.listaInsumos.insumos.size.toString(), Toast.LENGTH_LONG)
                .show()

            // Añadir el listener para el SearchView
            binding.sViewI.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
}