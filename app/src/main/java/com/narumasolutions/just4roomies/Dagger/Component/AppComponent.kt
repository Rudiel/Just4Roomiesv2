package com.narumasolutions.just4roomies.Dagger.Component

import com.narumasolutions.just4roomies.Dagger.Module.AppModule
import com.narumasolutions.just4roomies.Dagger.Module.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

}