package com.example.composemultiplateformex.domain.usecase

import com.example.composemultiplateformex.data.dto.toSingleProductEntity
import com.example.composemultiplateformex.domain.repository.IRepository
import kotlinx.coroutines.flow.flow

class GetSingleProductsUseCase(private val repository: IRepository) {
    operator fun invoke(productId:Int) = flow {
        val response = repository.getSingleProduct(productId).toSingleProductEntity()
        emit(response)
    }


}