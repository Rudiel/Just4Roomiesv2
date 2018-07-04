package com.narumasolutions.just4roomies.UI.RegisterPersonality

import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PersonalityViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    init {

    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun getPersonality(){

    }

    fun onRetrivePersonalityStart() {

    }

    fun onRetrivePersonalityFinish() {

    }

    fun onRetrivePersonalitySucces() {

    }

    fun onRetrivePersonalityError() {

    }


}