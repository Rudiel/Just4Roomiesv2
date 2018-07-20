package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.UI.BaseViewModel
import java.util.*

class RoomieViewModel : BaseViewModel() {

    private val tvRoomieName = MutableLiveData<String>()
    private val tvRoomieApellido = MutableLiveData<String>()
    private val tvRoomieDescripcion = MutableLiveData<String>()
    private val tvRoomieEdad = MutableLiveData<Int>()
    private val ivNacionalidad= MutableLiveData<Int>()

    fun bind(roomie: Roomie, context: Context) {
        tvRoomieName.value = roomie.Nombre
        tvRoomieApellido.value = roomie.Apellido
        tvRoomieDescripcion.value = roomie.Descripcion
        tvRoomieEdad.value = roomie.Edad

        val flag=context.resources.getIdentifier("flag_"+roomie.Nacionalidad.toLowerCase(Locale.ENGLISH),"drawable",context.packageName)

        ivNacionalidad.value= flag

    }

    fun getRoomieName(): MutableLiveData<String> {
        return tvRoomieName
    }

    fun getRoomieApellido(): MutableLiveData<String> {
        return tvRoomieApellido
    }

    fun getRoomieDescripcion(): MutableLiveData<String> {
        return tvRoomieDescripcion
    }

    fun getRoomieNacionality() : MutableLiveData<Int>{
        return ivNacionalidad
    }
}