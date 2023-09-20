package com.example.composemultiplateformex.ui.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemultiplateformex.domain.entity.ProductEntity

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItemCell(product: ProductEntity, onClick: (product: ProductEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(6.dp),
            topEnd = CornerSize(6.dp),
            bottomStart = CornerSize(6.dp),
            bottomEnd = CornerSize(6.dp)
        ),
        onClick ={onClick(product)},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.LightGray
        )
      //  onClick = {onClick(product)},
      //  backgroundColor = Color.Background
    ) {
        Column {
            KamelImage(
                asyncPainterResource(product.thumbnail?:""),
                "${product.category} by ${product.brand}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
            )

            Column( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
                ProductRating(product.rating)
                ProductBrandName(product.brand?:"Nothing")
                ProductName(product.title?:"Nothing")
                ProductPrice(product.price.toString())
            }

        }
    }
}

@Composable
fun WithTitle(title: String,textCompose: @Composable () -> Unit){
    buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold,color = Color.Red
        )) {
            append(title)
        }
        textCompose()

    }
}

@Composable
fun ProductBrandName(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.Black
    )
}

@Composable
fun ProductName(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = com.example.composemultiplateformex.ui.theme.Color.mainText
    )
}

@Composable
fun ProductPrice(title: String) {
        Text(
            modifier = Modifier
                .padding(2.dp),
            text = "₹$title",
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            maxLines = 1,
            color = Color.Black
        )
}

@Composable
fun ProductRating(rate: Float) {
    Row(
        modifier = Modifier
            .height(30.dp)
            .padding(2.dp)
    ) {
        for (i in 1..5) {
            if (i <= rate) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled star",
                    tint =Color.Yellow
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty star",
                    tint = Color.White
                )
            }
        }
    }
}