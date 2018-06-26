package com.narumasolutions.just4roomies.ViewModel

import com.narumasolutions.just4roomies.Just4RoomiesServices
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivityViewModel : BaseViewModel(){

    @Inject
    lateinit var  service : Just4RoomiesServices

    private lateinit var subscription : Disposable

    init{
        //loadPost()
    }

    private fun load(){
    }

}