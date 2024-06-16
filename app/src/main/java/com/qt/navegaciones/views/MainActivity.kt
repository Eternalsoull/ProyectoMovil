package com.qt.navegaciones

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.qt.navegaciones.databinding.ActivityMainBinding
import com.qt.navegaciones.models.Globals

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        Globals.initDatabase(this)
        binding.btnRegister.setOnClickListener(this)
        binding.btnRegisterRol.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

        // Verificar si hay una cédula guardada en caché
        val savedCedula = sharedPreferences.getString("cedula", null)
        if (savedCedula != null) {
            val intent = Intent(this, PantallaBienvenida::class.java)
            intent.putExtra("cedula", savedCedula) // Pasar la cédula a la pantalla de bienvenida
            startActivity(intent)
            finish() // Finalizar la actividad actual para que no pueda volver atrás
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRegister -> {
                val intent = Intent(this, PantallaRegistro::class.java)
                startActivity(intent)
            }
            R.id.btnRegisterRol -> {
                val intent = Intent(this, PantallaRegistroRol::class.java)
                startActivity(intent)
            }
            R.id.btnLogin -> {
                // Validar que la cédula y la contraseña coincidan con el usuario
                val cedula = binding.edtCedula.text.toString()
                val contrasena = binding.edtPassword.text.toString()
                val usuario = Globals.getdataBase(this)?.usuarioDao()?.getUsuarioByCedulaContrasena(cedula, contrasena)
                if (usuario != null) {
                    // Guardar la cédula en caché
                    val editor = sharedPreferences.edit()
                    editor.putString("cedula", cedula)
                    editor.apply()

                    // Redirigir a la pantalla de bienvenida
                    val intent = Intent(this, PantallaBienvenida::class.java)
                    intent.putExtra("cedula", cedula) // Pasar la cédula a la pantalla de bienvenida
                    startActivity(intent)
                    finish() // Finalizar la actividad actual para que no pueda volver atrás
                }
            }
        }
    }
}
