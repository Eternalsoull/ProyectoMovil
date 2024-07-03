package com.qt.navegaciones.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.PantallaBienvenida
import com.qt.navegaciones.PantallaRegistroRol
import com.qt.navegaciones.R
import com.qt.navegaciones.databinding.ActivityRegistrarClienteBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.ClienteEntity
import com.qt.navegaciones.models.database.entities.UsuarioEntity



class RegistrarClienteActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityRegistrarClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        binding.btnRegistrar.setOnClickListener(this)
        binding.btnListar.setOnClickListener(this)
        binding.btnVolverC.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnRegistrar -> {
                //guardar en una variable lo que esta en cache
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val cedula = sharedPreferences.getString("cedula", "")
                //si la cedula esta vacia mostrar un mensaje
                if (cedula == "") {
                    Toast.makeText(this, "No se ha iniciado sesion", Toast.LENGTH_LONG).show()
                    return
                }

                //crear el cliente con los campos del formulario y agregarlo a la base de datos
                var clienteEntity = ClienteEntity()
                clienteEntity.Cedula = binding.etCedula.text.toString()
                clienteEntity.Nombre = binding.etNombre.text.toString()
                clienteEntity.Direccion = binding.etDireccion.text.toString()
                clienteEntity.Telefono = binding.etTelefono.text.toString()
                //obtener el usuario por cedula para guardar el id del usuario que esta creando el cliente
                val usuario = Globals.getdataBase(this)?.usuarioDao()?.getUsuarioByCedula(cedula!!)
                clienteEntity.id_Usuario = usuario?.id_Usuario!!

                //si la cedula ya existe en la base de datos no se puede agregar, y si no existe se agrega
                if (Globals.getdataBase(this)?.clienteDao()
                        ?.getClienteByCedula(clienteEntity.Cedula) == null
                ) {
                    Globals.getdataBase(this)?.clienteDao()?.insertCliente(clienteEntity)
                } else {
                    Toast.makeText(this, "La cedula ya existe", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(this, "Se ha agregado un clienter", Toast.LENGTH_LONG).show()

            }

            R.id.btnListar -> {
                val intent = Intent(this, ListaClientes::class.java)
                startActivity(intent)

            }
            R.id.btnVolverC -> {
                val intent = Intent(this, PantallaBienvenida::class.java)
                startActivity(intent)
            }
        }
    }
}