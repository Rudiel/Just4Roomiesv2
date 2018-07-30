package com.narumasolutions.just4roomies.Dagger.Component

import com.narumasolutions.just4roomies.Dagger.Module.AppModule
import com.narumasolutions.just4roomies.Dagger.Module.NetModule
import com.narumasolutions.just4roomies.UI.Container.Fragments.Room.RoomViewModel
import com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies.SearchRoomiesViewModel
import com.narumasolutions.just4roomies.UI.Login.LoginViewModel
import com.narumasolutions.just4roomies.UI.Register.RegisterViewModel
import com.narumasolutions.just4roomies.UI.Home.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(loginViewModel: LoginViewModel)

    fun inject(registerViewModel: RegisterViewModel)

    fun inject(searchRoomiesViewModel: SearchRoomiesViewModel)

    fun inject(roomViewModel: RoomViewModel)

}