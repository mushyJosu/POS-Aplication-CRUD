package com.upchiapas.pointofsaleapp.products.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.upchiapas.pointofsaleapp.products.domain.repository.ProductsRepository

class ProductsViewModelFactory(
    //fabrica para ProductsRepository
    private val productsRepository: ProductsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(productsRepository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}