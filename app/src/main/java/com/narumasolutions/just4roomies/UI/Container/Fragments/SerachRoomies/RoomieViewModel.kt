package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.MutableLiveData
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.UI.BaseViewModel

class RoomieViewModel : BaseViewModel() {

    private val tvRoomieNombre = MutableLiveData<String>()
    private val tvRoomieApellido = MutableLiveData<String>()
    private val tvRoomieDescripcion = MutableLiveData<String>()

    fun bind(roomie: Roomie) {
        tvRoomieNombre.value = roomie.Nombre
        tvRoomieApellido.value = roomie.Apellido
        tvRoomieDescripcion.value = roomie.Apellido

    }

    fun getRoomieName(): MutableLiveData<String> {
        return tvRoomieNombre
    }

    fun getRoomieApellido(): MutableLiveData<String> {
        return tvRoomieApellido
    }

    fun getRoomieDescripcion(): MutableLiveData<String> {
        return tvRoomieDescripcion
    }
}