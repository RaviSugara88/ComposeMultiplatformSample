package com.example.composemultiplateformex.domain.repository

import com.example.composemultiplateformex.data.dto.Product
import com.example.composemultiplateformex.data.dto.ProductResponse


interface IRepository {
    suspend fun getAllProducts(): ProductResponse

    suspend fun getSingleProduct(productId: Int): Product
}