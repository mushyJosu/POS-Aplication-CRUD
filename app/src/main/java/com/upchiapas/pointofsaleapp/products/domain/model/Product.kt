package com.upchiapas.pointofsaleapp.products.domain.model

data class Product(
    //modelado de nuestro producto conforme nuestra API
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val categoryId: Int?,
    val createdAt: String
)
