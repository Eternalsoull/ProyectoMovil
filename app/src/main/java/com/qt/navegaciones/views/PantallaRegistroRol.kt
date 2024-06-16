package com.qt.navegaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qt.navegaciones.databinding.ActivityRegistroRolBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.RolEntity

class PantallaRegistroRol : AppCompatActivity(), View.OnClickListener {
    lateinit var binding : ActivityRegistroRolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroRolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        binding.btnRegistrarRol.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrarRol -> {
                var rolEntity = RolEntity()
                rolEntity.nombre_Rol = binding.etRol.text.toString()
                Globals.getdataBase(this)?.rolDao()?.insertRol(rolEntity)

                Toast.makeText(this, "Se ha agregado un rol", Toast.LENGTH_LONG).show()
                clearFields()
            }
        }
    }

    private fun clearFields() {
        binding.etRol.setText("")
    }
    //mostrar los roles que se agregaron en el tvRol
    override fun onResume() {
        super.onResume()
        var roles : List<RolEntity> = Globals.getdataBase(this)?.rolDao()?.getAllroles()!!
        var rolesString : String = ""
        for (rol in roles){
            rolesString += rol.nombre_Rol + "\n"
        }
        binding.tvRol.text = rolesString
    }
}