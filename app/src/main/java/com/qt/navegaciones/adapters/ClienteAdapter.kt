package com.qt.navegaciones.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.qt.navegaciones.R
import com.qt.navegaciones.models.Globals
import com.qt.navegaciones.models.Clientes
import com.qt.navegaciones.models.database.entities.ClienteEntity

class ClienteAdapter (context: Context, clientes: Clientes) : BaseAdapter(), Filterable {


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

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var view: View? = convertView
            if (view == null) {
                view = View.inflate(context, R.layout.item_clientes_adapter, null)
            }

            val cliente = filteredClientes.clientes[position]
            val txtName = view?.findViewById<TextView>(R.id.txtNameCliente)
            txtName?.text = cliente.Nombre
            val txtCedula = view?.findViewById<TextView>(R.id.txtCedulaC)
            txtCedula?.text = cliente.Cedula
            val txtTelefono = view?.findViewById<TextView>(R.id.txtTelefonoC)
            txtTelefono?.text = cliente.Telefono


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
                        filterResults.values = Clientes (filteredList as ArrayList<ClienteEntity>)
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

