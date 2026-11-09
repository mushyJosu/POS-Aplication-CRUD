package com.upchiapas.pointofsaleapp.products.presentation.viewmodels

import com.upchiapas.pointofsaleapp.products.domain.model.Product

sealed interface ProductsUiState{
    data object Idle: ProductsUiState
    data object Loading: ProductsUiState
    //Cargara la lista de products
    data class Success(val products: List<Product>): ProductsUiState
    class Error(val message: String): ProductsUiState

}