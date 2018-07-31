package com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Response.GetProfilesResponse
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.UI.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRoomiesViewModel : BaseViewModel() {

    @Inject
    lateinit var services: Just4RoomiesServices

    private var subscription: Disposable? = null

    val response: MutableLiveData<List<Roomie>> = MutableLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {

    }

    fun getRoomies() {

        val params = HashMap<String, Int>()

        params.set("start", 1)
        params.set("limit", 10)



        subscription = services.getUsers(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveRooomiesStart() }
                .doOnTerminate { onRetriveRoomiesFinish() }
                .subscribe(
                        { result -> onRetriveRoomiesSucces(result) },
                        { onRetriveRoomiesError(it) })

    }

    private fun onRetriveRooomiesStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetriveRoomiesFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetriveRoomiesError(throwable: Throwable) {

    }

    private fun onRetriveRoomiesSucces(getProfilesResponse: GetProfilesResponse) {
        response.value = getProfilesResponse.Roomies
    }

    /*fun onRetriveRoomiesSucces(response: ResponseBody){
            response.byteStream()
    }*/


}