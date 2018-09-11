package com.narumasolutions.just4roomies.UI.Container.Fragments.Room

import android.arch.lifecycle.MutableLiveData
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.Room
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response: MutableLiveData<Int> = MutableLiveData()

    init {

    }


    fun saveRoom(room: Room) {
        subscription = services.saveRoom(room)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveSaveRoomStart() }
                .doOnTerminate { onRetriveSaveRoomFinish() }
                .subscribe({ result -> onRetriveSaveRoomSucces() },
                        { onRetriveSaveRoomError() })
    }


    fun onRetriveSaveRoomStart() {

    }

    fun onRetriveSaveRoomFinish() {

    }


    fun onRetriveSaveRoomError() {

    }

    fun onRetriveSaveRoomSucces() {

    }


}