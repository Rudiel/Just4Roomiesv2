package com.narumasolutions.just4roomies.Model.Request

import android.databinding.BaseObservable

data class RegisterNew(val Nombre: String, val Apellido: String, val Email: String,val Imagen: String,  val Contrasenia: String) : BaseObservable()
