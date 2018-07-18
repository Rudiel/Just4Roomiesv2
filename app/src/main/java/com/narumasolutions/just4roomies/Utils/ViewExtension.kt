package com.narumasolutions.just4roomies.Utils

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.ContextWrapper

fun View.getParentActivity(): AppCompatActivity?{

    var context = this.context
    while (context is ContextWrapper){
        if(context is AppCompatActivity){
            return context
        }
        context= context.baseContext
    }

    return null
}