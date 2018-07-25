package com.narumasolutions.just4roomies

import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Request.Room
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.GetProfilesResponse
import com.narumasolutions.just4roomies.Model.Response.Roomie
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface Just4RoomiesServices {


    @POST("api/LoginUsuario")
    fun login(@Body user: User): Observable<Result<ResponseBody>>

    @GET("/api/GetProfiles/Page")
    fun getUsers(@QueryMap params: Map<String, Int>): Observable<GetProfilesResponse>

    @POST("api/Register")
    fun doRegister(@Body registerNew: RegisterNew): Observable<UserResponse>

    @POST("api/SaveRoom")
    fun saveRoom(@Body room: Room): Observable<ResponseBody>

}