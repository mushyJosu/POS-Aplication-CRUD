package com.upchiapas.pointofsaleapp.core.domain

sealed interface DataError{
    //medida para cuando no haya red
    data object NoInternet: DataError
    data object Timeout: DataError
    data class Htpp(val code: Int): DataError //errores 401
    data object Serialization: DataError
    data object Unknown: DataError
}

//respuesta a los repositories
sealed interface AppResultResult<out T>{
    data class Success<T>(val data: T): AppResultResult<T>
    data class Error(val error: DataError): AppResultResult<Nothing>
}