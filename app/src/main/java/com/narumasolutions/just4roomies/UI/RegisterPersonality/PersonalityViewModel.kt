package com.narumasolutions.just4roomies.UI.RegisterPersonality

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonalityViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response : MutableLiveData<Int> = MutableLiveData()
    val registerClickListener = View.OnClickListener { doRgister() }

    val nationClickListener = View.OnClickListener { showNationPicker()  }

    val showCountryPicker : MutableLiveData<Boolean> = MutableLiveData()

    init {


    }

    fun showNationPicker() {
        showCountryPicker.value = true
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun doRgister() {

        /*subscription = services.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveRegisterstart() }
                .doOnTerminate { onRetriveRegisterFinish() }
                .subscribe(
                        { onRetriveRegisterSucces() },
                        { onRetriveRegisterError() }
                )*/
    }

    fun onRetriveRegisterstart() {}

    fun onRetriveRegisterFinish() {}

    fun onRetriveRegisterSucces() {}

    fun onRetriveRegisterError() {}


}