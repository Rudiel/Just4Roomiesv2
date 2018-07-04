package com.narumasolutions.just4roomies.UI.Login

import android.arch.lifecycle.MutableLiveData
import android.util.Patterns
import android.view.View
import com.mukesh.countrypicker.fragments.CountryPicker
import com.mukesh.countrypicker.interfaces.CountryPickerListener
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val response: MutableLiveData<Int> = MutableLiveData()
    // val loginClickListener = View.OnClickListener { evaluatePassword(etUsuario.toString(), etPassword.toString()) }
    val loginClickListener = View.OnClickListener { doLogin() }

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



    private fun evaluatePassword(username: String, password: String) {

        val sizePattern = ObservableTransformer<String, String> { observable ->
            observable.map { it.trim() }
                    .filter { it.length > 6 }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Password muy corto"))
                        } else {
                            Single.error(it)
                        }
                    }.toObservable()
        }

        Observable.just(password).compose(sizePattern)
                .subscribe({}, {}, { onRetriveLoginError(503) }, { evaluateUsername(username) })

    }

    private fun evaluateUsername(username: String) {

        val emailPattern = ObservableTransformer<String, String> { observable ->
            observable.map { it.trim() }
                    .filter { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
                    .singleOrError()
                    .onErrorResumeNext {
                        if (it is NoSuchElementException) {
                            Single.error(Exception("Email no valido"))
                        } else {
                            Single.error(it)
                        }
                    }.toObservable()
        }

        // Observable.just(username).compose(emailPattern).subscribe({},{}, { onRetriveLoginError(502) }, { doLogin() })

        Observable.just(username).compose(emailPattern).subscribe({}, {}, {})


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

    fun onRetriveLoginError(code: Int) {
        response.value = code
    }

}