package com.example.composemultiplateformex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DefaultScreen(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      //  Text("Hello, ${getPlatformName()}")
        Image(
            painterResource("compose-multiplatform.xml"),
            null
        )
    }

}