package com.qt.navegaciones

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.databinding.ActivityRegistroBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.RolEntity
import com.qt.navegaciones.models.database.entities.UsuarioEntity


class PantallaRegistro : AppCompatActivity(), View.OnClickListener {
    lateinit var binding : ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        binding.btnRegistrar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                //crear el usuario con los campos del formulario y agregarlo a la base de datos, el rol es llave foranea por lo que se debe buscar el id del rol seleccionado
                var usuarioEntity = UsuarioEntity()
                usuarioEntity.cedula = binding.etCedula.text.toString()
                usuarioEntity.nombre = binding.etNombre.text.toString()
                usuarioEntity.contrasena = binding.etContrasena.text.toString()
                var roles : List<RolEntity> = Globals.getdataBase(this)?.rolDao()?.getAllroles()!!
                var rolId : Int = 0
                for (rol in roles){
                    if (rol.nombre_Rol == binding.spRol.selectedItem.toString()){
                        rolId = rol.id_Rol
                    }
                }
                usuarioEntity.id_Rol = rolId
                Globals.getdataBase(this)?.usuarioDao()?.insertUsuario(usuarioEntity)

                Toast.makeText(this, "Se ha agregado un usuario", Toast.LENGTH_LONG).show()
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