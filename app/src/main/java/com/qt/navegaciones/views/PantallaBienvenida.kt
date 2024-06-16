package com.qt.navegaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.databinding.ActivityBienvenidaBinding
import com.qt.navegaciones.models.Globals

class PantallaBienvenida: AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityBienvenidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCerrar.setOnClickListener(this)

        // Recuperar la cédula del intent
        val cedula = intent.getStringExtra("cedula")
        if (cedula != null) {
            val usuario = Globals.getdataBase(this)?.usuarioDao()?.getUsuarioByCedula(cedula)
            binding.tvNombre.text = usuario?.nombre
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCerrar -> {
                // Limpiar la cédula guardada en caché
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.remove("cedula")
                editor.apply()

                // Redirigir a la pantalla de inicio
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finalizar la actividad actual para que no pueda volver atrás
            }
        }
    }
}
