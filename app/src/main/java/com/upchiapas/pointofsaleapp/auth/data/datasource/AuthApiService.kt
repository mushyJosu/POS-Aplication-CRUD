package com.upchiapas.pointofsaleapp.auth.data.datasource

import com.upchiapas.pointofsaleapp.auth.data.dto.LoginRequestDto
import com.upchiapas.pointofsaleapp.auth.data.dto.RegisterRequestDto
import com.upchiapas.pointofsaleapp.auth.data.dto.TokenReponseDto
import com.upchiapas.pointofsaleapp.auth.data.dto.UserResponseDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): TokenReponseDto

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto
    ): UserResponseDto
}