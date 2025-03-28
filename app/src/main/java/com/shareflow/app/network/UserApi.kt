package com.shareflow.app.network

import com.shareflow.app.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/register")
    suspend fun registerUser(@Body user: User): retrofit2.Response<String>
}
