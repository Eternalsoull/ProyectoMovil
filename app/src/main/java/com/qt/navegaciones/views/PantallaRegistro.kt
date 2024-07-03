package com.qt.navegaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.databinding.ActivityRegistroBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.RolEntity
import com.qt.navegaciones.models.database.entities.UsuarioEntity
import com.qt.navegaciones.views.ListaUsuarios


class PantallaRegistro : AppCompatActivity(), View.OnClickListener {
    lateinit var binding : ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        binding.btnRegistrar.setOnClickListener(this)
        binding.btnListarUsuarios.setOnClickListener(this)
        binding.btnVolver.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                //crear el usuario con los campos del formulario y agregarlo a la base de datos, el rol es llave foranea por lo que se debe buscar el id del rol seleccionado
                var usuarioEntity = UsuarioEntity()
                usuarioEntity.cedula = binding.etCedula.text.toString()
                usuarioEntity.nombre = binding.etNombre.text.toString()
                usuarioEntity.contrasena = binding.etContrasena.text.toString()
                var roles: List<RolEntity> = Globals.getdataBase(this)?.rolDao()?.getAllroles()!!
                var rolId: Int = 0
                for (rol in roles) {
                    if (rol.nombre_Rol == binding.spRol.selectedItem.toString()) {
                        rolId = rol.id_Rol
                    }
                }
                usuarioEntity.id_Rol = rolId

                //si la cesula ya existe en la base de datos no se puede agregar, y si no existe se agrega
                if (Globals.getdataBase(this)?.usuarioDao()?.getUsuarioByCedula(usuarioEntity.cedula) == null) {
                        Globals.getdataBase(this)?.usuarioDao()?.insertUsuario(usuarioEntity)
                } else {
                    Toast.makeText(this, "La cedula ya existe", Toast.LENGTH_LONG).show()
                }

                Toast.makeText(this, "Se ha agregado un usuario", Toast.LENGTH_LONG).show()
            }

            R.id.btnListarUsuarios -> {
                val intent = Intent(this, ListaUsuarios::class.java)
                startActivity(intent)
            }
            R.id.btnVolver -> {
                //si la el shared preferences no tiene la cedula, se redirige a la pantalla de inicio
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val cedula = sharedPreferences.getString("cedula", "")
                if (cedula == "") {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    //si la cedula esta en el shared preferences se redirige a la pantalla de bienvenida
                    val intent = Intent(this, PantallaBienvenida::class.java)
                    startActivity(intent)
                }
            }
        }
    }



    override fun onResume() {
        super.onResume()
        var roles : List<RolEntity> = Globals.getdataBase(this)?.rolDao()?.getAllroles()!!
        var rolesString : MutableList<String> = mutableListOf()
        for (rol in roles){
            rolesString.add(rol.nombre_Rol)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rolesString)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spRol.adapter = adapter

    }


}