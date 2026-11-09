package com.upchiapas.pointofsaleapp.products.data.repository

import com.upchiapas.pointofsaleapp.products.data.datasource.ProductosApiService
import com.upchiapas.pointofsaleapp.products.data.dto.ProductRequestDto
import com.upchiapas.pointofsaleapp.products.data.dto.toDomain   // CAMBIO A: import de toDomain
import com.upchiapas.pointofsaleapp.products.domain.model.Product
import com.upchiapas.pointofsaleapp.products.domain.repository.ProductsRepository

class ProductRepositoryImpl (
    private val apiService: ProductosApiService
): ProductsRepository {

    override suspend fun getProducts(): List<Product> {
        val dtoList = apiService.getProducts()
        return dtoList.map { dto -> dto.toDomain() }
    }

    override suspend fun getProductById(id: Int): Product {
        val dto = apiService.getProductById(id)
        return dto.toDomain()
    }

    override suspend fun createProduct(
        name: String, price: Double, stock: Int, categoryId: Int?
    ): Product {
        val request = ProductRequestDto(name, price, stock, categoryId)
        val dto = apiService.createProduct(request)
        return dto.toDomain()
    }

    override suspend fun updateProduct(id: Int, name: String, price: Double, stock: Int, categoryId: Int?
    ): Product {
        val request = ProductRequestDto(name, price, stock, categoryId)
        val dto = apiService.updateProduct(id, request)
        return dto.toDomain()
    }

    override suspend fun deleteProduct(id: Int) {
        apiService.deleteProduct(id)
    }
}