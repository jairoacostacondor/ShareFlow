package com.shareflow.shareflowbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val nombre: String,
    val email: String,
    val contrasena: String
)
