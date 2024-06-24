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
import com.qt.navegaciones.models.Insumos
import com.qt.navegaciones.models.database.entities.InsumoEntity

class InsumoAdapter(context: Context, insumos: Insumos) : BaseAdapter(), Filterable {


        private var context: Context = context
        private var originalInsumos: Insumos = insumos
        private var filteredInsumos: Insumos = insumos

        override fun getCount(): Int {
            return filteredInsumos.insumos.size
        }

        override fun getItem(position: Int): Any {
            return filteredInsumos.insumos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var view: View? = convertView
            if (view == null) {
                view = View.inflate(context, R.layout.item_insumos_adapter, null)
            }

            val insumos = filteredInsumos.insumos[position]
            val txtidP = view?.findViewById<TextView>(R.id.txtIdProducto)
            val txtdescripcion = view?.findViewById<TextView>(R.id.txtDescripcion)


            // Formatear "idP: " en negrita
            val idPBoldText = "Id Producto: "
            val idPNormalText = insumos.id_Producto
            val idPSpannable = SpannableString(idPBoldText + idPNormalText)
            idPSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, idPBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            txtidP?.text = idPSpannable

            // Formatear "descripcion: " en negrita
            val descripcionBoldText = "Descripcion: "
            val descripcionNormalText = insumos.Descripcion
            val descripcionSpannable = SpannableString(descripcionBoldText + descripcionNormalText)
            descripcionSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, descripcionBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            txtdescripcion?.text = descripcionSpannable



            return view
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val filterResults = FilterResults()
                    if (constraint.isNullOrEmpty()) {
                        filterResults.count = originalInsumos.insumos.size
                        filterResults.values = originalInsumos
                    } else {
                        val filteredList = originalInsumos.insumos.filter { it.Descripcion.contains(constraint.toString(), true) }
                        filterResults.count = filteredList.size
                        filterResults.values = Insumos(filteredList as ArrayList<InsumoEntity>)
                    }
                    return filterResults
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    if (results != null && results.values is Insumos) {
                        filteredInsumos = results.values as Insumos
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }



