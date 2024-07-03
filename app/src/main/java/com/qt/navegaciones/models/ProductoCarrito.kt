package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.ProductoEntity

data class ProductoCarrito(
    val producto: ProductoEntity,
    var cantidad: Int
)
