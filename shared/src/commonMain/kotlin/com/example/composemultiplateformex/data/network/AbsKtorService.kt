package com.example.composemultiplateformex.data.network

import com.example.composemultiplateformex.data.dto.Product
import com.example.composemultiplateformex.data.dto.ProductResponse

interface AbsKtorService {
    suspend fun getAllProduct():ProductResponse

    suspend fun getSingleProduct(productId: Int):Product

}