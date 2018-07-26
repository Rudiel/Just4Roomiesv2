package com.narumasolutions.just4roomies.UI.RegisterPersonality

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.google.gson.Gson
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.CreatePersonality
import com.narumasolutions.just4roomies.Model.Response.ErrorResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PersonalityViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response: MutableLiveData<Any> = MutableLiveData()

    val pbVisibility: MutableLiveData<Int> = MutableLiveData()

    init {

    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun registerPersonality(personality: CreatePersonality) {

        subscription = services.createPersonality(personality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveRegisterstart() }
                .doOnTerminate { onRetriveRegisterFinish() }
                .subscribe(
                        { result -> onRetriveRegisterSucces(result.response()) },
                        { onRetriveRegisterError() }
                )
    }

    private fun onRetriveRegisterstart() {
        pbVisibility.value = View.VISIBLE
    }

    private fun onRetriveRegisterFinish() {
        pbVisibility.value = View.GONE
    }

    private fun onRetriveRegisterSucces(responseBody: Response<ResponseBody>?) {
        if (responseBody?.code() == 200) {
            this.response.value = responseBody
        } else {
            val errorResponse = Gson().fromJson(responseBody?.errorBody()?.string(), ErrorResponse::class.java)
            this.response.value = errorResponse
        }
    }

    private fun onRetriveRegisterError() {
        this.response.value = null
    }


}