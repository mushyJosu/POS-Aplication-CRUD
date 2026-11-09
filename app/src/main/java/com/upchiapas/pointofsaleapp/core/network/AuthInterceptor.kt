package com.upchiapas.pointofsaleapp.core.network

import android.security.KeyChain
import com.upchiapas.pointofsaleapp.core.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        val token = sessionManager.getToken()

        //Si hay token, lo inyectamos en el header
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        //Dejamos que la petición continúe su viaje hacia Render
        return chain.proceed(requestBuilder.build())
    }
}