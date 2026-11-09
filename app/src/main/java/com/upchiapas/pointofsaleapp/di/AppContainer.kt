package com.upchiapas.pointofsaleapp.di

import android.content.Context
import com.upchiapas.crudapp.core.network.NetworkModule
import com.upchiapas.pointofsaleapp.auth.data.datasource.AuthApiService
import com.upchiapas.pointofsaleapp.auth.data.repository.AuthRepositoryImpl
import com.upchiapas.pointofsaleapp.auth.domain.repository.AuthRepository
import com.upchiapas.pointofsaleapp.core.network.AuthInterceptor
import com.upchiapas.pointofsaleapp.core.session.SessionManager
import com.upchiapas.pointofsaleapp.products.data.datasource.ProductosApiService
import com.upchiapas.pointofsaleapp.products.data.repository.ProductRepositoryImpl
import com.upchiapas.pointofsaleapp.products.domain.repository.ProductsRepository

class AppContainer(private val context: Context) {

    private val sessionManager = SessionManager(context)
    private val authInterceptor = AuthInterceptor(sessionManager)

    //Red
    private val okHttpClient = NetworkModule.provideOkHttpClient(authInterceptor)
    private val retrofit = NetworkModule.provideRetrofit(okHttpClient)

    //Módulo de Autenticación
    private val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authApiService, sessionManager)
    }

    //Módulo de Productos
    private val productsApiService: ProductosApiService by lazy {
        retrofit.create(ProductosApiService::class.java)
    }

    val productsRepository: ProductsRepository by lazy {
        ProductRepositoryImpl(productsApiService)
    }
}