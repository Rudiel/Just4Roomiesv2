package com.narumasolutions.just4roomies

import android.app.Application
import com.narumasolutions.just4roomies.Dagger.Component.AppComponent
import com.narumasolutions.just4roomies.Dagger.Component.DaggerAppComponent
import com.narumasolutions.just4roomies.Dagger.Module.AppModule

class Just4RoomiesApp : Application(){

    companion object {
    lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

}