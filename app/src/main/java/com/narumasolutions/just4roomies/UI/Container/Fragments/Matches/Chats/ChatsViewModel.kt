package com.narumasolutions.just4roomies.UI.Container.Fragments.Matches.Chats

import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatsViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null


    fun getChats(userId: String) {

        val params = HashMap<String, String>()

        params.set("IdUsuario", "a6e5e330-9955-4caa-adfb-f")

        subscription = services.getChats(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe()
    }


    fun onRetriveChatsStart() {

    }

    fun onRetriveChatsFinish() {

    }

    fun onRetriveChatsError() {

    }


    fun onRetriveChatsSucces() {

    }


}