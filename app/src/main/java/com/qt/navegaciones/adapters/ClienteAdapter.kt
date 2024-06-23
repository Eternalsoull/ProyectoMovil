package com.qt.navegaciones.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.Clientes
import com.qt.navegaciones.models.database.entities.ClienteEntity

class ClienteAdapter(context: Context, clientes: Clientes) : BaseAdapter(), Filterable {

    private var context: Context = context
    private var originalClientes: Clientes = clientes
    private var filteredClientes: Clientes = clientes

    override fun getCount(): Int {
        return filteredClientes.clientes.size
    }

    override fun getItem(position: Int): Any {
        return filteredClientes.clientes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.item_clientes_adapter, null)
        }

        val cliente = filteredClientes.clientes[position]
        val txtName = view?.findViewById<TextView>(R.id.txtNameCliente)
        val txtCedula = view?.findViewById<TextView>(R.id.txtCedulaC)
        val txtTelefono = view?.findViewById<TextView>(R.id.txtTelefonoC)

        // Formatear "Nombre: " en negrita
        val nombreBoldText = "Nombre: "
        val nombreNormalText = cliente.Nombre
        val nombreSpannable = SpannableString(nombreBoldText + nombreNormalText)
        nombreSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, nombreBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtName?.text = nombreSpannable

        // Formatear "Cedula: " en negrita
        val cedulaBoldText = "Cedula: "
        val cedulaNormalText = cliente.Cedula
        val cedulaSpannable = SpannableString(cedulaBoldText + cedulaNormalText)
        cedulaSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, cedulaBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtCedula?.text = cedulaSpannable

        // Formatear "Telefono: " en negrita
        val telefonoBoldText = "Telefono: "
        val telefonoNormalText = cliente.Telefono
        val telefonoSpannable = SpannableString(telefonoBoldText + telefonoNormalText)
        telefonoSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, telefonoBoldText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtTelefono?.text = telefonoSpannable

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filterResults.count = originalClientes.clientes.size
                    filterResults.values = originalClientes
                } else {
                    val filteredList = originalClientes.clientes.filter { it.Cedula.contains(constraint.toString(), true) }
                    filterResults.count = filteredList.size
                    filterResults.values = Clientes(filteredList as ArrayList<ClienteEntity>)
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.values is Clientes) {
                    filteredClientes = results.values as Clientes
                    notifyDataSetChanged()
                }
            }
        }
    }
}
