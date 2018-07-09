package com.narumasolutions.just4roomies

import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Just4RoomiesServices {

    @POST("api/Login")
    fun login(): Observable<UserResponse>

    @GET("/api/GetUsers")
    fun getUsers(): Observable<List<User>>

    @POST("Register")
    fun doRegister(@Body registerNew: RegisterNew): Observable<UserResponse>

}