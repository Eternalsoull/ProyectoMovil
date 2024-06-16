package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.UsuarioEntity

class Usuarios {
    var usuarios = ArrayList<UsuarioEntity>()

    constructor(usuarios: ArrayList<UsuarioEntity>){
        this.usuarios = usuarios
    }

    fun getArrayListFullName(): ArrayList<String> {
        var list = ArrayList<String>()
        for (usuario in usuarios) {
            list.add(usuario.getFullName())
        }
        return list
    }
}