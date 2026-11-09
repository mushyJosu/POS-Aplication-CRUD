package com.upchiapas.pointofsaleapp.products.domain.repository
import com.upchiapas.pointofsaleapp.products.domain.model.Product
interface ProductsRepository {
    //definimos nuestro contracto
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
    suspend fun createProduct(name: String, price: Double, stock: Int, categoryId: Int? = null): Product
    suspend fun updateProduct(id: Int, name: String, price: Double, stock: Int, categoryId: Int?): Product
    suspend fun deleteProduct(id: Int)
}
