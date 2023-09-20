package com.example.composemultiplateformex.ui.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composemultiplateformex.domain.entity.ProductEntity
import com.example.composemultiplateformex.ui.LoginScree
import com.example.composemultiplateformex.ui.screen.DefaultScreen
import com.example.composemultiplateformex.ui.screen.ErrorScreen
import com.example.composemultiplateformex.ui.screen.LoadingScreen
import com.example.composemultiplateformex.ui.screen.ProductListScreen
import com.example.composemultiplateformex.ui.viewmodel.ProductsViewModel
import kotlinx.coroutines.flow.update

@Composable
fun HomePage(viewModel: ProductsViewModel) {
    val uiState by viewModel.products.collectAsState()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(uiState){
            is ProductsViewModel.ProductsState.Error -> {
                ErrorScreen((uiState as ProductsViewModel.ProductsState.Error<List<ProductEntity>>).error.message)
            }            is ProductsViewModel.ProductsState.Idle -> {
            DefaultScreen()
        }
            is ProductsViewModel.ProductsState.Loading -> {
                LoadingScreen()
            }
            is ProductsViewModel.ProductsState.Login -> {
                LoginScree()
            }
            is ProductsViewModel.ProductsState.Success -> (
                    uiState as ProductsViewModel.ProductsState.Success<List<ProductEntity>>).data.apply {
                ProductListScreen(this)
            }
            else -> {
                LoginScree()
            }
        }

        /* if (status==0){

         }else if (status==1){
             LoginScree()
         }else if (status==2){
             LoadingScreen()
         }else{
             DefaultScreen()
         }*/
    }
}