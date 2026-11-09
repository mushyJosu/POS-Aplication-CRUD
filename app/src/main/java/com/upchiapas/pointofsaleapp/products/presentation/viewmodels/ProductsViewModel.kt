package com.upchiapas.pointofsaleapp.products.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upchiapas.pointofsaleapp.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel (
    //Maneja todo el CRUD de los products
    private val productsRepository: ProductsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Idle)
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    //inmediatamente se crea este VM para cargar products
    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = ProductsUiState.Loading
            try {
                val list = productsRepository.getProducts()
                _uiState.value = ProductsUiState.Success(list)
            } catch (e: Exception) {
                _uiState.value = ProductsUiState.Error(e.message ?: "Error al cargar los productos")
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            try {
                productsRepository.deleteProduct(id)
                loadProducts()
            } catch (e: Exception) {
                _uiState.value =
                    ProductsUiState.Error(e.message ?: "No se pudo eliminar: ${e.message}")
            }
        }
    }
    fun addProduct(name: String, price: Double, stock: Int) {
        viewModelScope.launch {
            try {
                productsRepository.createProduct(name, price, stock, null)
                loadProducts()
            } catch (e: Exception) {
                _uiState.value =
                    ProductsUiState.Error(e.message ?: "No se pudo agregar el producto")
            }
        }
    }
}