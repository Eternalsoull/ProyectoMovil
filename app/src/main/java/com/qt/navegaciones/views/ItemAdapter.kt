package com.qt.navegaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.databinding.ItemAdapterBinding
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.views.ListaUsuarios


class ItemAdapter : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ItemAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = intent
        binding.btnVolver.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //eliminar el usuario seleccionado
            R.id.btnVolver -> {
                //rediriigir a la pantalla de bienvenida
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}