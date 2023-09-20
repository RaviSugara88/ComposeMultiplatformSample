package com.example.composemultiplateformex.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.composemultiplateformex.ui.theme.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItemScreen(product: ProductEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick ={onClick()},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.LightGray
        )
      //  backgroundColor = Color.Background

    ) {
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
            KamelImage(
                asyncPainterResource(product.thumbnail?:""),
                "${product.category} by ${product.brand}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
            )
            ProductRating(product.rating)
            ProductBrandName(product.brand?:"Nothing")
            ProductName(product.title?:"Nothing")
            ProductPrice(product.price.toString())
            DescriptionSection(product.description?:"")
            ImagesSection(product.images)
        }

    }
}



@Composable
fun WithTitle(title: String,textCompose: @Composable () -> Unit){
    buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold,color = Color.red
        )
        ) {
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
        color = androidx.compose.ui.graphics.Color.Black
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
        color = Color.mainText
    )
}

@Composable
fun ProductPrice(title: String) {
    Text(
        modifier = Modifier
            .padding(2.dp),
        text = " ₹$title",
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = androidx.compose.ui.graphics.Color.Black
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
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled star",
                    tint = androidx.compose.ui.graphics.Color.Yellow
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Empty star",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}

@Composable
fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun ImagesSection(images:List<String>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
        content = {
            items(images.size) {
                KamelImage(
                    asyncPainterResource(images[it]),
                    "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
                )
            }
        }
    )
}