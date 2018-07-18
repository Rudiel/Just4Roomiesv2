package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.MutableLiveData
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Response.GetProfilesResponse
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class SearchRoomiesViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response: MutableLiveData<Int> = MutableLiveData()

    init {

    }

    fun getRoomies() {

        val params = HashMap<String,Int>()

        params.set("start",1)
        params.set("limit",10)


        subscription = services.getUsers(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveRooomiesStart() }
                .doOnTerminate { onRetriveRoomiesFinish() }
                .subscribe(
                        { result -> onRetriveRoomiesSucces(result) },
                        {onRetriveRoomiesError(it)})

    }

    fun onRetriveRooomiesStart() {

    }

    fun onRetriveRoomiesFinish() {

    }

    fun onRetriveRoomiesError(throwable: Throwable) {
            response.value=400
    }

    fun onRetriveRoomiesSucces(getProfilesResponse: GetProfilesResponse) {
            getProfilesResponse.Roomies
            response.value =200
    }

    /*fun onRetriveRoomiesSucces(response: ResponseBody){
            response.byteStream()
    }*/

}