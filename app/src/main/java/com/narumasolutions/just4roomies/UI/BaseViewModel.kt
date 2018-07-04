package com.narumasolutions.just4roomies.ViewModel

import android.arch.lifecycle.ViewModel
import com.narumasolutions.just4roomies.Dagger.Component.AppComponent
import com.narumasolutions.just4roomies.Dagger.Component.DaggerAppComponent
import com.narumasolutions.just4roomies.Dagger.Module.NetModule
import com.narumasolutions.just4roomies.UI.Login.LoginViewModel
import com.narumasolutions.just4roomies.UI.Register.RegisterViewModel

abstract class BaseViewModel : ViewModel(){

        private val injector: AppComponent = DaggerAppComponent
                .builder()
                .netModule(NetModule)
                .build()

    init {
        inject()
    }

    private fun inject(){
        when(this){
            is MainActivityViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
            is RegisterViewModel -> injector.inject(this)
        }
    }

}