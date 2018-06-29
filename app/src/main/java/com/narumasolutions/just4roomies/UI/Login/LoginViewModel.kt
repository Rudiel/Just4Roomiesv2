package com.narumasolutions.just4roomies.UI.Login

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.R.id.etPassword
import com.narumasolutions.just4roomies.R.id.etUsuario
import com.narumasolutions.just4roomies.ViewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val response: MutableLiveData<Int> = MutableLiveData()
    val loginClickListener = View.OnClickListener { doLogin(etUsuario.toString(),etPassword.toString()) }

    private var subscription: Disposable? = null


    init {
    }

    fun doLogin(usuario: String, passwprd: String) {


        subscription = services.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveLoginStart() }
                .doOnTerminate { onRetriveLoginFinish() }
                .subscribe(
                        { onRetriveLoginSucces(it) },
                        { onRetriveLoginError(it) }
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
        response.value = userResponse.code
    }

    fun onRetriveLoginError(throwable: Throwable) {
        response.value = throwable.hashCode()
    }

}