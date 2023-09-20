package com.example.composemultiplateformex.di

import com.example.composemultiplateformex.data.network.KtorBuilder.createHttpClient
import com.example.composemultiplateformex.data.network.KtorService
import com.example.composemultiplateformex.data.repository.Repository
import com.example.composemultiplateformex.domain.usecase.GetAllProductsUseCase
import com.example.composemultiplateformex.domain.usecase.GetSingleProductsUseCase

object DiModule {
//    private val
    private val httpClient = createHttpClient()
    private val service = KtorService(httpClient)
    private val repository = Repository(service)
    val getAllProductUseCase = GetAllProductsUseCase(repository)
    val getSingleProductsUseCase = GetSingleProductsUseCase(repository)

}