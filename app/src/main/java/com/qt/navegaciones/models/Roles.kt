package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.RolEntity

class Roles {
    var roles = ArrayList<RolEntity>()

    constructor(roles: ArrayList<RolEntity>){
        this.roles = roles
    }
}