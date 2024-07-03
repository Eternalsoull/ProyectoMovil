package com.qt.navegaciones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.VentaAgrupada
import java.text.SimpleDateFormat
import java.util.Locale

class VentaAgrupadaAdapter(context: Context, ventasAgrupadas: List<VentaAgrupada>) :
    ArrayAdapter<VentaAgrupada>(context, 0, ventasAgrupadas) {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val ventaAgrupada = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_venta_agrupada, parent, false)
        }

        val fechaTextView: TextView = convertView!!.findViewById(R.id.tvFechaVenta)
        val detallesTextView: TextView = convertView.findViewById(R.id.tvDetallesVentas)

        fechaTextView.text = "Fecha: ${dateFormat.format(ventaAgrupada?.fecha)}"

        val detalles = ventaAgrupada?.ventas?.joinToString("\n") { venta ->
            "Producto: ${venta.Nombre}, Cantidad: ${venta.Cantidad}, Precio Total: ${venta.PrecioTotal}"
        }
        detallesTextView.text = detalles

        return convertView
    }
}
