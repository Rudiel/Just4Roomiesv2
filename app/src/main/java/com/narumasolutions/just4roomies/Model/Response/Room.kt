package com.narumasolutions.just4roomies.Model.Response

import android.databinding.BaseObservable

data class Room(val Id: String,
                val Amueblado: Boolean,
                val Latitud: String,
                val Longitud: String,
                val Costo: Double) : BaseObservable()
