package com.qt.navegaciones.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.Productos
import com.qt.navegaciones.models.database.entities.ProductoEntity

class ProductoAdapter (context: Context, productos: Productos) : BaseAdapter(), Filterable {


        private var context: Context = context
        private var originalProductos:  Productos = productos
        private var filteredProductos: Productos = productos

        override fun getCount(): Int {
            return filteredProductos.productos.size
        }

        override fun getItem(position: Int): Any {
            return filteredProductos.productos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var view: View? = convertView
            if (view == null) {
                view = View.inflate(context, R.layout.item_productos_adapter, null)
            }

            val producto = filteredProductos.productos[position]
            val txtNombre = view?.findViewById<TextView>(R.id.txtNombreP)
            val txtPrecio = view?.findViewById<TextView>(R.id.txtPrecioP)


            // Formatear "Nombre: " en negrita
            val nombreBoldText = "Nombre: "
            val nombreNormalText = producto.Nombre
            val nombreSpannable = SpannableString(nombreBoldText + nombreNormalText)
            nombreSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, nombreBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            txtNombre?.text = nombreSpannable

            // Formatear "Precio: " en negrita
            val precioBoldText = "Precio: "
            val precioNormalText = producto.Precio
            val precioSpannable = SpannableString(precioBoldText + precioNormalText)
            precioSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, precioSpannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            txtPrecio?.text = precioSpannable


            return view
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val filterResults = FilterResults()
                    if (constraint.isNullOrEmpty()) {
                        filterResults.count = originalProductos.productos.size
                        filterResults.values = originalProductos
                    } else {
                        val filteredList = originalProductos.productos.filter { it.Nombre.contains(constraint.toString(), true) }
                        filterResults.count = filteredList.size
                        filterResults.values = Productos(filteredList as ArrayList<ProductoEntity>)
                    }
                    return filterResults
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    if (results != null && results.values is Productos) {
                        filteredProductos = results.values as Productos
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

