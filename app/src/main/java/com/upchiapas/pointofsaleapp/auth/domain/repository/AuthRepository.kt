package com.upchiapas.pointofsaleapp.auth.domain.repository

interface AuthRepository{
    //para login contrato a cumplir
    suspend fun login(email:String, password:String)
    suspend fun register(name:String, email:String, password:String)
    fun logout()
    fun isUserLogged():Boolean
}
