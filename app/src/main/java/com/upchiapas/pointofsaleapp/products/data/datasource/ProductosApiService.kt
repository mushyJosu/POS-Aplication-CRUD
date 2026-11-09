package com.upchiapas.pointofsaleapp.products.data.datasource

import com.upchiapas.pointofsaleapp.products.data.dto.ProductDto
import com.upchiapas.pointofsaleapp.products.data.dto.ProductRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductosApiService {
    //declaramos nuestros endpoints como nuestra API

    @GET("products/")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto

    @POST("products/")
    suspend fun createProduct(
        @Body product: ProductRequestDto
    ): ProductDto

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: ProductRequestDto
    ): ProductDto

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    )
}