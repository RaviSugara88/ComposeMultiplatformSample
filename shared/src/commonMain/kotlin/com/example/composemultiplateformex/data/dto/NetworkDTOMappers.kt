package com.example.composemultiplateformex.data.dto

import com.example.composemultiplateformex.domain.entity.ProductEntity


// Mappers
fun Product.toSingleProductEntity(): ProductEntity
        = ProductEntity(
    id = this.id,
    title= this.title,
    description = this.description,
    price = this.price ,
    discountPercentage = this.discountPercentage,
    rating = this.rating,
    stock = this.stock,
    brand = this.brand,
    category = this.category,
    thumbnail = this.thumbnail,
    images = this.images
)
fun ProductResponse.toProductEntity():List<ProductEntity> = this.products.map { it.toSingleProductEntity() }