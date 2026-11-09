package com.upchiapas.crudapp.core.network

import com.upchiapas.pointofsaleapp.core.network.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {

    //URL api
    private const val BASE_URL = "https://point-of-sale-api-dtfo.onrender.com/"

    private val json = Json { ignoreUnknownKeys = true }

    // Función para proveer el cliente OkHttp con nuestro Interceptor
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}