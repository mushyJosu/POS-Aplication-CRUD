package com.upchiapas.pointofsaleapp.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email:String,
    val password:String
)

@Serializable
data class RegisterRequestDto(
    @SerialName("full_name") val name: String,
    val email:String,
    val password:String
)

@Serializable
data class TokenReponseDto(
    @SerialName("access_token") val accessToken:String
)

@Serializable
data class UserResponseDto(
    val id:Int,
    @SerialName("full_name") val name: String,
    val email:String
)