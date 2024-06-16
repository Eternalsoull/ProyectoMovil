package com.qt.navegaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.qt.navegaciones.adapters.CarAdapter
import com.qt.navegaciones.databinding.ActivityPantalla3Binding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.Cars
import com.qt.navegaciones.models.database.entities.CarEntity

class Pantalla3 : AppCompatActivity() {

   lateinit var binding: ActivityPantalla3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantalla3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var cars : Cars = Cars(Globals.getdataBase(this)?.carDao()?.getAllCars()!! as ArrayList<CarEntity>)
        var adapter : CarAdapter = CarAdapter(this, cars)
        binding.lstPersonas.adapter = adapter
        Toast.makeText(this, Globals.listaCarros.cars.size.toString(), Toast.LENGTH_LONG).show()


    }
}