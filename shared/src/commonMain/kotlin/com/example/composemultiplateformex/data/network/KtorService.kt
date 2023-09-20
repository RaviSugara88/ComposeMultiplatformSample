package com.example.composemultiplateformex.data.network

import com.example.composemultiplateformex.data.dto.Product
import com.example.composemultiplateformex.data.dto.ProductResponse
import com.example.composemultiplateformex.data.getUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorService(private val httpClient: HttpClient):AbsKtorService {
    override suspend fun getAllProduct(): ProductResponse
    = httpClient.get(Endpoints.GET_ALL_PRODUCT).body()
    override suspend fun getSingleProduct(productId: Int): Product
    = httpClient.get(Endpoints.GET_SINGLE_PRODUCT.getUrl(productId)).body()
}
