package com.example.composemultiplateformex.data.repository

import com.example.composemultiplateformex.data.dto.Product
import com.example.composemultiplateformex.data.dto.ProductResponse
import com.example.composemultiplateformex.data.network.AbsKtorService
import com.example.composemultiplateformex.domain.repository.IRepository

class Repository(private val ktorService: AbsKtorService):IRepository {

    override suspend fun getAllProducts(): ProductResponse = ktorService.getAllProduct()

    override suspend fun getSingleProduct(productId: Int): Product =
        ktorService.getSingleProduct(productId)

}