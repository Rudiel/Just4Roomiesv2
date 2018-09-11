package com.narumasolutions.just4roomies.Model.Response

import android.databinding.BaseObservable

data class Chat(val Emisor: String,
                val Receptor: String,
                val id: String,
                val IdChat: String,
                val Estatus: Int,
                val Hora: String,
                val Fecha: String) : BaseObservable()