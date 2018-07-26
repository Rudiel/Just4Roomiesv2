package com.narumasolutions.just4roomies.Model.Request

import android.databinding.BaseObservable

data class CreatePersonality(val IdUsuario: String,
                             val Nacionalidad: String,
                             val Idioma: String,
                             val Edad: Int,
                             val Latitud: Double,
                             val Longitud: Double,
                             val Fumas: Boolean,
                             val Mascotas: Boolean,
                             val Activo: Boolean,
                             val Fiestero: Boolean,
                             val Estudias: Boolean,
                             val Cocinas: Boolean) : BaseObservable()