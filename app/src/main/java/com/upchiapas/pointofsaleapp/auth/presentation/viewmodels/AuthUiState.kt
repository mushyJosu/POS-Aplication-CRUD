package com.upchiapas.pointofsaleapp.auth.presentation.viewmodels

sealed interface AuthUiState{
    //Las unicas formas en las que existe la pantalla
    data object Idle: AuthUiState
    data object Loading: AuthUiState
    data class Error(val message: String): AuthUiState
    data object Success: AuthUiState
}