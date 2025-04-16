package com.shareflow.shareflowbackend.models

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val nombre: String,
    val email: String
)
