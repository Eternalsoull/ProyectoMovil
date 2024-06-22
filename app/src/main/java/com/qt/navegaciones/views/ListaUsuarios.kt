package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.MainActivity
import com.qt.navegaciones.R
import com.qt.navegaciones.adapters.UsuarioAdapter
import com.qt.navegaciones.databinding.ActivityPantalla3Binding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.Usuarios
import com.qt.navegaciones.models.database.entities.UsuarioEntity

class ListaUsuarios : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityPantalla3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuarios: Usuarios = Usuarios(Globals.getdataBase(this)?.usuarioDao()?.getAllusuarios()!! as ArrayList<UsuarioEntity>)
        val adapter: UsuarioAdapter = UsuarioAdapter(this, usuarios)

        binding.btnVolver2.setOnClickListener(this)
        binding.lstPersonas.adapter = adapter
        Toast.makeText(this, Globals.listaUsuarios.usuarios.size.toString(), Toast.LENGTH_LONG).show()

        // Añadir el listener para el SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
                // Eliminar todos los usuarios
                Globals.getdataBase(this)?.usuarioDao()?.deleteAll()
                // Redirigir a la pantalla de bienvenida
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

