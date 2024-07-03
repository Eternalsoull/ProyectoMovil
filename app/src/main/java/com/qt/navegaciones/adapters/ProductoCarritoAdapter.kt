package com.qt.navegaciones.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.ProductoCarrito

class ProductoCarritoAdapter(context: Context, productos: ArrayList<ProductoCarrito>) :
    ArrayAdapter<ProductoCarrito>(context, 0, productos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val productoCarrito = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto_carrito, parent, false)
        }

        val nombreTextView: TextView = convertView!!.findViewById(R.id.tvNombreProducto)
        val precioTextView: TextView = convertView.findViewById(R.id.tvPrecioProducto)
        val cantidadTextView: TextView = convertView.findViewById(R.id.tvCantidadProducto)
        val precioTotalTextView: TextView = convertView.findViewById(R.id.tvPrecioTotalProducto)

        nombreTextView.text = "Nombre: ${productoCarrito?.producto?.Nombre}"
        precioTextView.text = "Precio: ${productoCarrito?.producto?.Precio}"
        cantidadTextView.text = "Cantidad: ${productoCarrito?.cantidad}"
        precioTotalTextView.text = "Precio Total: ${productoCarrito?.producto?.Precio?.times(productoCarrito.cantidad)}"

        return convertView
    }
}
