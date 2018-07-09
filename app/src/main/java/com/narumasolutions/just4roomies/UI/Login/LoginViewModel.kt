package com.narumasolutions.just4roomies.UI.Login

import android.arch.lifecycle.MutableLiveData
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import com.narumasolutions.just4roomies.Utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    val response: MutableLiveData<Int> = MutableLiveData()
    val navigateToMain = MutableLiveData<Event<String>>()


    private var subscription: Disposable? = null

    init {


    }


    fun doLogin() {

        subscription = services.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveLoginStart() }
                .doOnTerminate { onRetriveLoginFinish() }
                .subscribe(
                        { onRetriveLoginSucces(it) },
                        { onRetriveLoginError(500) }
                )
    }


    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun onRetriveLoginFinish() {

    }

    fun onRetriveLoginStart() {

    }

    fun onRetriveLoginSucces(userResponse: UserResponse) {
        response.value = userResponse.Code
    }

    fun onRetriveLoginError(code: Int) {
        response.value = code
    }

}