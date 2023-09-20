package com.example.composemultiplateformex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailListUI(emailList: EmailList){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(6.dp),
            topEnd = CornerSize(6.dp),
            bottomStart = CornerSize(6.dp),
            bottomEnd = CornerSize(6.dp)
        ),
        onClick ={},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
        //  onClick = {onClick(product)},
        //  backgroundColor = Color.Background
    ){

        Row(Modifier.fillMaxWidth().padding(10.dp)) {
            Box(modifier = Modifier
                .padding(4.dp)
                .height(65.dp)
                .width(65.dp)
                .aspectRatio(1f)
                .background(Color.Blue, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${emailList.emailTitle[0].toUpperCase()}",
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center
                )
            }
           /* Image(
                imageVector = Icons.Outlined.Person,
                contentDescription = "contentDescription",
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
                    .clip(CircleShape)

            )*/

            Column(Modifier.padding(start = 5.dp)) {
                EmailTitle(emailList.emailTitle?:"Nothing")
                ProductName(emailList.subject?:"Nothing")
                EmailDes(emailList.des)
            }


        }

    }
}
data class EmailList(
    val emailTitle:String,
    val subject : String,
    val des : String
)

@Composable
fun EmailDes(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = com.example.composemultiplateformex.ui.theme.Color.lightGray
    )
}
@Composable
fun EmailTitle(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        textAlign = TextAlign.Start,
        maxLines = 1,
        color = Color.Black
    )
}

@Composable
fun CircleImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier.clip(CircleShape)
        )
    }
}