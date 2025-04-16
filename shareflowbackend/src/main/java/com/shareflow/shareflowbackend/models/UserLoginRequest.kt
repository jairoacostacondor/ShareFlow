package com.shareflow.shareflowbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val email: String,
    val contrasena: String
)
