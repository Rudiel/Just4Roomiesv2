package com.narumasolutions.just4roomies

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.narumasolutions.just4roomies.Dagger.Component.AppComponent
import com.narumasolutions.just4roomies.Dagger.Component.DaggerAppComponent
import com.narumasolutions.just4roomies.Dagger.Module.AppModule

class Just4RoomiesApp : MultiDexApplication(){

    companion object {
    lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

        MultiDex.install(this)
    }

}