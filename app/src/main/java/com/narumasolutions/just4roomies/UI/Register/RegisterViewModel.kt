package com.narumasolutions.just4roomies.UI.Register

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RegisterViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response: MutableLiveData<UserResponse> = MutableLiveData()

    val pbVisibility : MutableLiveData<Int> = MutableLiveData()


    fun doRegister(register: RegisterNew) {

        subscription = services.doRegister(register)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{onRetriveRegisterStart()}
                .doOnTerminate{onRetriveRegisterFinish()}
                .subscribe({result-> onRetriveRegisterSucces(result)},{onRetriveRegisterError()})


    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    private fun onRetriveRegisterStart() {
            pbVisibility.value= View.VISIBLE
    }

    private fun onRetriveRegisterFinish() {
            pbVisibility.value=View.GONE
    }

    private fun onRetriveRegisterSucces(response: UserResponse)
    {
        this.response.value= response
    }

    private fun onRetriveRegisterError() {

        this.response.value = UserResponse("",500,"")
    }

}