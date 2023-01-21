package com.example.data.auth

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AuthResponse(
    @SerialName("access_token")
    val accessToken: String
)