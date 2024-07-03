package com.qt.navegaciones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.database.entities.VentaEntity

class VentaAdapter(context: Context, ventas: ArrayList<VentaEntity>) :
    ArrayAdapter<VentaEntity>(context, 0, ventas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val venta = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_venta, parent, false)
        }

        val nombreTextView: TextView = convertView!!.findViewById(R.id.tvNombreProducto)
        val precioTextView: TextView = convertView.findViewById(R.id.tvPrecioProducto)
        val cantidadTextView: TextView = convertView.findViewById(R.id.tvCantidadProducto)
        val precioTotalTextView: TextView = convertView.findViewById(R.id.tvPrecioTotalProducto)
        val fechaTextView: TextView = convertView.findViewById(R.id.tvFechaVenta)

        nombreTextView.text = "Nombre: ${venta?.Nombre}"
        precioTextView.text = "Precio: ${venta?.Precio}"
        cantidadTextView.text = "Cantidad: ${venta?.Cantidad}"
        precioTotalTextView.text = "Precio Total: ${venta?.PrecioTotal}"
        fechaTextView.text = "Fecha: ${venta?.Fecha}"

        return convertView
    }
}
