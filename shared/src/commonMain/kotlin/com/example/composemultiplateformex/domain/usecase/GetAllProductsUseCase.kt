package com.example.composemultiplateformex.domain.usecase

import com.example.composemultiplateformex.data.dto.toProductEntity
import com.example.composemultiplateformex.domain.entity.ProductEntity
import com.example.composemultiplateformex.domain.repository.IRepository
import kotlinx.coroutines.flow.flow

class GetAllProductsUseCase(private val repository:IRepository) {
    private val productEntities = mutableListOf<ProductEntity>()

    operator fun invoke() = flow {
        val response = repository.getAllProducts().toProductEntity()
        productEntities.addAll(response)
        emit(productEntities)

    }


}