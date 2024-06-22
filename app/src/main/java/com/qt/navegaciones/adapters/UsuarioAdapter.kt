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
import com.qt.navegaciones.models.Usuarios
import com.qt.navegaciones.models.database.entities.UsuarioEntity

class UsuarioAdapter(context: Context, usuarios: Usuarios) : BaseAdapter(), Filterable {

    private var context: Context = context
    private var originalUsuarios: Usuarios = usuarios
    private var filteredUsuarios: Usuarios = usuarios

    override fun getCount(): Int {
        return filteredUsuarios.usuarios.size
    }

    override fun getItem(position: Int): Any {
        return filteredUsuarios.usuarios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.item_adapter, null)
        }

        val usuario = filteredUsuarios.usuarios[position]
        val txtName = view?.findViewById<TextView>(R.id.txtFullName)
        txtName?.text = usuario.getFullName()
        val txtCedula = view?.findViewById<TextView>(R.id.txtCedula)
        txtCedula?.text = usuario.cedula
        val txtRol = view?.findViewById<TextView>(R.id.txtRol)
        txtRol?.text = Globals.getdataBase(context)?.rolDao()?.getRolById(usuario.id_Rol)?.nombre_Rol

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filterResults.count = originalUsuarios.usuarios.size
                    filterResults.values = originalUsuarios
                } else {
                    val filteredList = originalUsuarios.usuarios.filter { it.cedula.contains(constraint.toString(), true) }
                    filterResults.count = filteredList.size
                    filterResults.values = Usuarios(filteredList as ArrayList<UsuarioEntity>)
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.values is Usuarios) {
                    filteredUsuarios = results.values as Usuarios
                    notifyDataSetChanged()
                }
            }
        }
    }
}
