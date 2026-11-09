package com.upchiapas.pointofsaleapp.products.data.mapper

import com.upchiapas.pointofsaleapp.products.data.dto.ProductDto
import com.upchiapas.pointofsaleapp.products.domain.model.Product

class ProductMapper {
    fun ProductDto.toDomain(): Product{
        return Product(
            //Se mapean aunque tengan nombres diferentes
            id= this.id,
            name= this.name,
            price= this.price,
            stock= this.stock,
            categoryId= this.categoryId,
            createdAt= this.createdAt
    )
    }
}