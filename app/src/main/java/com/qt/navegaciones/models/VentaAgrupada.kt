package com.qt.navegaciones.models

import com.qt.navegaciones.models.database.entities.VentaEntity
import java.util.Date

data class VentaAgrupada(
    val fecha: Date,
    val ventas: List<VentaEntity>
)
