package com.narumasolutions.just4roomies.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.narumasolutions.just4roomies.Just4RoomiesServices
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.R
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var service: Just4RoomiesServices

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val loginClickListener = View.OnClickListener { getRoomies() }

    init {
        getRoomies()
    }


    private fun getRoomies() {

        subscription = service.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetriveRoomiesListStart() }
                .doOnTerminate { onRetriveRoomiesListFinish() }
                .subscribe(
                        { result -> onRetriveRoomiesListSucces(result) },
                        { onRetriveRoomiesListError() }
                )

    }

    private fun onRetriveRoomiesListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetriveRoomiesListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetriveRoomiesListSucces(users: List<User>) {

    }

    private fun onRetriveRoomiesListError() {
        errorMessage.value = R.string.chat_misroomies
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}