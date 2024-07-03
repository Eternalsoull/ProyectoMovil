package com.qt.navegaciones

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.qt.navegaciones.adapters.VentaAgrupadaAdapter
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.VentaAgrupada
import com.qt.navegaciones.models.database.entities.VentaEntity

class ListarVentasActivity : AppCompatActivity() {

    private lateinit var lvVentas: ListView
    private lateinit var adapter: VentaAgrupadaAdapter
    private val ventasAgrupadas = ArrayList<VentaAgrupada>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_ventas)

        lvVentas = findViewById(R.id.lvVentas)

        adapter = VentaAgrupadaAdapter(this, ventasAgrupadas)
        lvVentas.adapter = adapter

        cargarVentas()
    }

    private fun cargarVentas() {
        val db = Globals.getdataBase(this)
        val ventas: List<VentaEntity> = db?.ventaDao()?.getAllVentas() ?: listOf()

        val ventasPorFecha = ventas.groupBy { it.Fecha }
        for ((fecha, ventas) in ventasPorFecha) {
            ventasAgrupadas.add(VentaAgrupada(fecha, ventas))
        }

        adapter.notifyDataSetChanged()
    }
}
