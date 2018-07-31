package com.narumasolutions.just4roomies.UI.Login

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.ErrorResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import com.narumasolutions.just4roomies.Utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import org.xml.sax.Parser
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    val response: MutableLiveData<Any> = MutableLiveData()
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
                        { onRetriveLoginSucces(it.response()) },
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
    fun onRetriveLoginSucces(responseBody: retrofit2.Response<ResponseBody>?) {
        if (responseBody?.code() == 200) {
            response.value = responseBody.body()
        } else {
            Log.d("ERROR_RES",""+ responseBody?.errorBody()?.toString())
            val errorResponse = Gson().fromJson(responseBody?.errorBody()?.string(), ErrorResponse::class.java)
            response.value = errorResponse
        }
    }

    fun onRetriveLoginError(throwable: Throwable) {
        response.value = null
    }

}