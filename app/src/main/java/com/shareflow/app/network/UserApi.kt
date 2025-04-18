package com.shareflow.app.network

import com.shareflow.app.models.User
import com.shareflow.app.models.UserLoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/register")
    suspend fun registerUser(@Body user: User): Response<String>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: UserLoginRequest): Response<String>

}
