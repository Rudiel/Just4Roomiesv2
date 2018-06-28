package com.narumasolutions.just4roomies.Model.Request

import android.databinding.BaseObservable
import android.text.TextUtils
import android.util.Patterns


data class User(val user: String, val pass: String) :BaseObservable(){

    val isDataValid : Boolean

    get() = (!TextUtils.isEmpty(getUserName()))
            && Patterns.EMAIL_ADDRESS.matcher(getUserName()).matches()
            && getPassword().length > 6

    fun getUserName(): String{
        return user
    }

     fun getPassword() : String {
        return pass
    }
}
