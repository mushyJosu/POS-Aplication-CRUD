package com.upchiapas.pointofsaleapp.products.data.dto

import com.upchiapas.pointofsaleapp.products.domain.model.Product
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ProductDto(
    //model para recibir datos de la API
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("stock") val stock: Int,
    @SerialName("category_id") val categoryId: Int?,
    @SerialName("created_at") val createdAt: String

)
data class ProductCreateDto(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("stock") val stock: Int,
    @SerialName("category_id") val categoryId: Int?
)

//molde para ENVIAR datos a la API
@Serializable
data class ProductRequestDto(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("stock") val stock: Int,
    @SerialName("category_id") val categoryId: Int?
)
fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        stock = this.stock,
        categoryId = this.categoryId,
        createdAt = this.createdAt
    )
}
