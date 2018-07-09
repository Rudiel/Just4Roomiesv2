package com.narumasolutions.just4roomies.Model.Response

import android.databinding.BaseObservable

data class UserResponse(val Message: String, val Code: Int, val UserId : String) : BaseObservable()
