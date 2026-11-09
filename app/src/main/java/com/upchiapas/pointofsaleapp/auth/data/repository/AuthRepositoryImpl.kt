package com.upchiapas.pointofsaleapp.auth.data.repository

import com.upchiapas.pointofsaleapp.auth.data.datasource.AuthApiService
import com.upchiapas.pointofsaleapp.auth.data.dto.LoginRequestDto
import com.upchiapas.pointofsaleapp.auth.data.dto.RegisterRequestDto
import com.upchiapas.pointofsaleapp.auth.domain.repository.AuthRepository
import com.upchiapas.pointofsaleapp.core.session.SessionManager

class AuthRepositoryImpl (
    //implemtacion de la interfaz AuthRepository
    private val apiService: AuthApiService,
    private val sessionManager: SessionManager
    ) : AuthRepository {
    override suspend fun login(email: String, password: String) {
        //Empaquetar los datos en el DTO para FastAPI
        val request = LoginRequestDto(email, password)

        val response = apiService.login(request)
        sessionManager.saveToken(response.accessToken)
    }

    override suspend fun register(name: String, email: String, password: String) {
        val request = RegisterRequestDto(name, email, password)
        apiService.register(request)
    }

    override fun logout() {
        sessionManager.clearToken() //verificar
    }

    override fun isUserLogged(): Boolean {
        return !sessionManager.getToken().isNullOrEmpty()
    }
}