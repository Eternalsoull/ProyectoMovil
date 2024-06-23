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
import com.qt.navegaciones.databinding.ActivityListarClientesBinding
import com.qt.navegaciones.databinding.ActivityPantalla3Binding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.Clientes
import com.qt.navegaciones.models.database.entities.ClienteEntity

class ListaClientes : AppCompatActivity(), View.OnClickListener {
        lateinit var binding: ActivityListarClientesBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityListarClientesBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val clientes: Clientes = Clientes(Globals.getdataBase(this)?.clienteDao()?.getAllclientes()!! as ArrayList<ClienteEntity>)
            val adapter: ClienteAdapter = ClienteAdapter(this, clientes)


            binding.lstClientes.adapter = adapter
            Toast.makeText(this, Globals.listaClientes.clientes.size.toString(), Toast.LENGTH_LONG).show()

            // Añadir el listener para el SearchView
            binding.sViewClientes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
                R.id.btnVolver2 -> {

                    // Redirigir a la pantalla de bienvenida
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


