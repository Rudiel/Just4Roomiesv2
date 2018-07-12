package com.narumasolutions.just4roomies

import com.narumasolutions.just4roomies.Model.Request.RegisterNew
import com.narumasolutions.just4roomies.Model.Request.User
import com.narumasolutions.just4roomies.Model.Response.UserResponse
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Just4RoomiesServices {


    @POST("api/Login")
    fun login(@Body user: User): Observable<ResponseBody>

    @GET("/api/GetUsers")
    fun getUsers(): Observable<List<User>>

    @POST("Register")
    fun doRegister(@Body registerNew: RegisterNew): Observable<UserResponse>

}