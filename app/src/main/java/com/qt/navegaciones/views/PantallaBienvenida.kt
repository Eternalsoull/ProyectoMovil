package com.qt.navegaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.databinding.ActivityBienvenidaBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.views.RegistrarClienteActivity
import com.qt.navegaciones.views.RegistrarInsumoActivity
import com.qt.navegaciones.views.RegistrarProductoActivity
import com.qt.navegaciones.views.RegistrarVehiculoActivity

class PantallaBienvenida: AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityBienvenidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCerrar.setOnClickListener(this)
        binding.btnRoles.setOnClickListener(this)
        binding.btnUsuarios.setOnClickListener(this)
        binding.btnClientes.setOnClickListener(this)
        binding.btnInsumos.setOnClickListener(this)
        binding.btnProductos.setOnClickListener(this)
        binding.btnVehiculos.setOnClickListener(this)
        binding.btnVentas.setOnClickListener(this)
        Nombre()
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
            R.id.btnRoles -> {
                val intent = Intent(this, PantallaRegistroRol::class.java)
                startActivity(intent)
            }
            R.id.btnUsuarios -> {
                val intent = Intent(this, PantallaRegistro::class.java)
                startActivity(intent)
            }
            R.id.btnClientes -> {
                //PASAR LA CEDULA DEL USUARIO LOGUEADO
                val cedula = intent.getStringExtra("cedula")
                intent.putExtra("cedula", cedula)
                val intent = Intent(this, RegistrarClienteActivity::class.java)
                startActivity(intent)
            }
            R.id.btnInsumos -> {
                val intent = Intent(this, RegistrarInsumoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnProductos -> {
                val intent = Intent(this, RegistrarProductoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnVehiculos -> {
                val intent = Intent(this, RegistrarVehiculoActivity::class.java)
                startActivity(intent)
            }
            R.id.btnVentas -> {
                val intent = Intent(this, RegistrarVentaActivity::class.java)
                startActivity(intent)
            }
        }
    }
     fun Nombre(){
        val cedula = intent.getStringExtra("cedula")
        if (cedula != null) {
            val usuario = Globals.getdataBase(this)?.usuarioDao()?.getUsuarioByCedula(cedula)
            binding.tvNombre.text = usuario?.nombre
            //si el rol es administrador mostrar el boton de btnUsuarios
            if (usuario?.id_Rol == 1){
                binding.btnUsuarios.visibility = View.VISIBLE
                binding.btnRoles.visibility = View.VISIBLE
            }
            else{
                binding.btnUsuarios.visibility = View.GONE
                binding.btnRoles.visibility = View.GONE
            }
        }
    }
}
