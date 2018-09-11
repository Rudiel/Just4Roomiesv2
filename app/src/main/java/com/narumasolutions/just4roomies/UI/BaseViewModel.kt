package com.narumasolutions.just4roomies.UI

import android.arch.lifecycle.ViewModel
import com.narumasolutions.just4roomies.Dagger.Component.AppComponent
import com.narumasolutions.just4roomies.Dagger.Component.DaggerAppComponent
import com.narumasolutions.just4roomies.Dagger.Module.NetModule
import com.narumasolutions.just4roomies.UI.Container.Fragments.Matches.Chats.ChatsViewModel
import com.narumasolutions.just4roomies.UI.Container.Fragments.Room.RoomViewModel
import com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies.SearchRoomiesViewModel
import com.narumasolutions.just4roomies.UI.Home.MainActivityViewModel
import com.narumasolutions.just4roomies.UI.Login.LoginViewModel
import com.narumasolutions.just4roomies.UI.Register.RegisterViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: AppComponent = DaggerAppComponent
            .builder()
            .netModule(NetModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainActivityViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
            is RegisterViewModel -> injector.inject(this)
            is SearchRoomiesViewModel -> injector.inject(this)
            is RoomViewModel -> injector.inject(this)
            is ChatsViewModel -> injector.inject(this)
        }
    }

}