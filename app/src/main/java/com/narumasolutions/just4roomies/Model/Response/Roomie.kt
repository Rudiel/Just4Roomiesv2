package com.narumasolutions.just4roomies.Model.Response

import android.databinding.BaseObservable

data class Roomie(val Apellido: String, val Nombre: String, val Descripcion: String, val Edad : Int, val Nacionalidad: String) : BaseObservable()