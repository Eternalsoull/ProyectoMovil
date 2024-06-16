package com.qt.navegaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qt.navegaciones.databinding.ActivityPantalla2Binding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.database.entities.CarEntity

class Pantalla2 : AppCompatActivity(), View.OnClickListener {
     lateinit var binding : ActivityPantalla2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
 binding.btnAceptar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnAceptar -> {
                var carEntity = CarEntity()
                carEntity.marca = binding.txtMarcaCarro.text.toString()
                carEntity.modelo = binding.txtModeloCarro.text.toString()
                carEntity.color = binding.txtColorCarro.text.toString()
                carEntity.placa = binding.txtPlacaCarro.text.toString()
                carEntity.url = binding.txtUrlCarro.text.toString()
                Globals.getdataBase(this)?.carDao()?.insertCar(carEntity)

                Toast.makeText(this, "Se ha agregado un carro", Toast.LENGTH_LONG).show()
                clearFields()
            }
        }

    }

    private fun clearFields() {
        binding.txtMarcaCarro.setText("")
        binding.txtModeloCarro.setText("")
        binding.txtColorCarro.setText("")
        binding.txtPlacaCarro.setText("")
    }
}


