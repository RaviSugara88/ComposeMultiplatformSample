package com.example.composemultiplateformex.ui.viewmodel

import com.example.composemultiplateformex.di.DiModule
import com.example.composemultiplateformex.domain.entity.ProductEntity
import com.example.composemultiplateformex.domain.usecase.GetAllProductsUseCase
import com.example.composemultiplateformex.domain.usecase.GetSingleProductsUseCase
import com.example.composemultiplateformex.utils.CustomMessage
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.composemultiplateformex.utils.Result
import com.example.composemultiplateformex.utils.asResult
import kotlinx.coroutines.flow.collectLatest

class ProductsViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase = DiModule.getAllProductUseCase,
    private val getSingleProductsUseCase: GetSingleProductsUseCase = DiModule.getSingleProductsUseCase
) : ViewModel() {
    private val _products =
        MutableStateFlow<ProductsState<List<ProductEntity>>>(ProductsState.Idle())

    val products = _products.asStateFlow()

    val _product =
        MutableStateFlow<ProductsState<ProductEntity>>(ProductsState.Idle())
    val product = _product.asStateFlow()

    init {
        setIntent(ProductsViewModel.ProductIntent.GetAllProduct)
    }

    fun setIntent(intent: ProductIntent){
        when(intent){
            is ProductIntent.GetAllProduct -> fetchAllProducts()
            is ProductIntent.GetSingleProduct -> fetchSingleProducts(intent.productId)
        }
    }
    private fun fetchAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().asResult().collectLatest {result->
                when(result){
                    is Result.Error -> _products.update { ProductsState.Error(result.exception) }
                    is Result.Idle -> _products.update { ProductsState.Idle() }
                    is Result.Loading -> _products.update { ProductsState.Loading() }
                    is Result.Success -> _products.update { ProductsState.Success(result.data) }
                }
            }
        }
    }

    private fun fetchSingleProducts(productId:Int) {
        viewModelScope.launch {
            val productApi = getSingleProductsUseCase(productId).asResult().collectLatest {result->
                when(result){
                    is Result.Error -> _product.update { ProductsState.Error(result.exception) }
                    is Result.Idle -> _product.update { ProductsState.Idle() }
                    is Result.Loading -> _product.update { ProductsState.Loading() }
                    is Result.Success -> _product.update { ProductsState.Success(result.data) }
                }
            }
        }
    }

    sealed interface ProductsState<Entity> {

        class Loading<Entity>: ProductsState<Entity>

        class Idle<Entity> : ProductsState<Entity>

        data class Success<Entity>(val data: Entity) : ProductsState<Entity>

        data class Error<Entity>(val error: CustomMessage) : ProductsState<Entity>

        class Email<Entity> : ProductsState<Entity>
        class UserProfile<Entity> : ProductsState<Entity>
        class Login<Entity> : ProductsState<Entity>

        class Register<Entity> : ProductsState<Entity>
    }

    sealed class ProductIntent{
        object GetAllProduct:ProductIntent()
        data class GetSingleProduct(val productId:Int):ProductIntent()
    }
}