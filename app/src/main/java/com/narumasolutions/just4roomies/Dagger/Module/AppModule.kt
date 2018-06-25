package com.narumasolutions.just4roomies.Dagger.Module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(internal val context: Context) {

    @Provides
    @Singleton
    internal fun priveContext(): Context {
        return context
    }
}