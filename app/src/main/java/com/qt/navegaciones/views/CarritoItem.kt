package com.qt.navegaciones.models.database.entities

data class CarritoItem(
    val id_Producto: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val precio: Double,
)
