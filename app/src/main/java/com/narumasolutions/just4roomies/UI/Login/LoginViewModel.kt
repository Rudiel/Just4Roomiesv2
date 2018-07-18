package com.narumasolutions.just4roomies.UI.Login

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import com.narumasolutions.just4roomies.Utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    val response: MutableLiveData<Int> = MutableLiveData()
    val navigateToMain = MutableLiveData<Event<String>>()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()



    private var subscription: Disposable? = null

    init {


    }


    fun doLogin(user: User) {

        subscription = services.login(user)
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
        loadingVisibility.value = View.GONE
    }

    fun onRetriveLoginStart() {
        loadingVisibility.value = View.VISIBLE
    }

   /* fun onRetriveLoginSucces(userResponse: UserResponse) {
        response.value = userResponse.Code
    }*/
    fun onRetriveLoginSucces(responseBody: ResponseBody){
       responseBody.bytes()
       response.value = 200
   }

    fun onRetriveLoginError(throwable: Throwable) {
        response.value = 400
    }

}