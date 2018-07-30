package com.narumasolutions.just4roomies.Model.Request

data class Room(
        val IdUsuario: String,
        val FechaDisponibilidad: String,
        val Amueblado: Boolean,
        val Costo: Double,
        val Latitud: Double,
        val Longitud: Double,
        val imagen1: String,
        val imagen2: String,
        val imagen3: String)