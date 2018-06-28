package com.narumasolutions.just4roomies.Dagger.Component

import com.narumasolutions.just4roomies.Dagger.Module.AppModule
import com.narumasolutions.just4roomies.Dagger.Module.NetModule
import com.narumasolutions.just4roomies.ViewModel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)

}