package com.example.composemultiplateformex.commonView


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R

import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource


//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
/*fun appIcon(){
    Image(
        painterResource(R.drawable.logo),
        contentDescription = "",
        modifier = Modifier
            .height(130.dp)
            .width(130.dp)
    )
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample(block:(text:String)->Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        singleLine = true,
        onValueChange = { text = it },
        label = { Text("") },
        textStyle = TextStyle(color = Black, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    block(text)
}

/** We can used [CustomOutlinedTextField] for all type text field like [Number,Password,Single Line , MultiLine]*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value:String,
    onValueChange: (String)->Unit,
    label:String,
    leadingIconImageVector: ImageVector,
    leadingIconDescription:String,
    isSingleLine:Boolean = false,
    isPasswordField:Boolean =false,
    isPasswordVisible:Boolean=false,
    onVisibilityChange:(Boolean)->Unit={false},
    keyBoardOption:KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError:Boolean = false,
    errorMessage:String="",
    textLength:Int = 0


) {
    OutlinedTextField(
        value = value,
        textStyle = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif),
        singleLine = isSingleLine,
        onValueChange = {
        if (textLength>0){
            if (it.length <= textLength){
                onValueChange(it)
            }
        }else{
            onValueChange(it)
        } },
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth(),
        label = { Text(label, style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif), modifier = Modifier.padding(top = 2.dp)) },
        leadingIcon ={
            Icon(
                imageVector = leadingIconImageVector,
                contentDescription = leadingIconDescription,
                tint = if (showError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            )
        },
        isError = showError,
        trailingIcon ={
            if (showError && !isPasswordField) Icon(imageVector = Icons.Filled.Warning, contentDescription ="show Error" )
/*
            if (isPasswordField){
                IconButton(onClick = {
                    onVisibilityChange(!isPasswordVisible)
                }) {
                    Icon(painter = if (isPasswordVisible) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(id = R.drawable.baseline_visibility_off_24)
                        , contentDescription = "Toggle password visibility")

                }
            }
*/

        },
        visualTransformation = when{
            isPasswordField && isPasswordVisible -> VisualTransformation.None
            isPasswordField -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        keyboardOptions = keyBoardOption,
        keyboardActions = keyboardActions,
        )
    if (showError){
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = TextStyle(
                fontSize = 12.sp
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .offset(y = (-8).dp)
                .fillMaxWidth(0.9f)
        )
    }

}



/** Simple text field just like Android XML */


@Composable
fun CustomMultilineSimpleTextField(
    value : String,
    onValueChange : (String) ->Unit,
    modifier: Modifier = Modifier,
    hintText: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLine : Int =4
){

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        maxLines = maxLine,
        decorationBox = {innerTextField ->
            Box(modifier = modifier) {
                if (value.isEmpty()){
                    Text(text = hintText,
                        color = Color.Blue)
                }
                innerTextField()
            }
        }

    )
}


@Composable
fun HeadingText(text: String){
   Text(text =text,
       modifier =Modifier.padding(start = 16.dp, end = 16.dp),
       style = TextStyle(
       fontSize = 22.sp,
       fontWeight = FontWeight.Bold,
       color = Black,

   ) )

}
@Composable
fun SimpleText(des:String,text: String,lineHeight:TextUnit){
    Text(text ="$des\n$text",
        modifier =Modifier.padding(start = 16.dp, end = 16.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Gray,
            lineHeight = lineHeight
            )
    )

}

@Composable
fun SimpleText(des:String,text: String,textStyle: TextStyle){
    Text(text ="$des\n$text",
        modifier =Modifier.padding(start = 16.dp, end = 16.dp),
        style =textStyle
    )

}
@Composable
fun ButtonText(text: String){
    Text(text =text,
        modifier =Modifier.padding(start = 16.dp, end = 16.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            ) )

}

@Composable
fun StringResourceText() {
    Text("",
        color = Color.Blue,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(150.dp))
}

//@Preview(showBackground = true)
@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

@Composable
fun DifferentFonts() {
    Column {
        Text("Hello World", fontFamily = FontFamily.Serif)
        Text("Hello World", fontFamily = FontFamily.SansSerif)
    }
}

@Composable
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

@Composable
fun ParagraphStyle() {
    Text(
        buildAnnotatedString {
          //  withStyle(style = ParagraphStyle()) {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Hello\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append("World\n")
                }
                append("Compose")
            }
      //  }
    )
}
@Composable
fun LongText() {
    Text("hello ".repeat(50), maxLines = 2)
}

@Composable
fun OverflowedText() {
    Text("Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun MultiColorText() {
    val GradientColors = listOf(Cyan, Yellow, Green)
    Text(
        text = "hello this is andoid demio application releted to etx5tt",
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = GradientColors
            )
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun MultiColourEditText(){
    val GradientColors = listOf(Cyan, Yellow, Green, Black, Red)
    var text by remember { mutableStateOf("") }
    val brush = remember {
        Brush.linearGradient(
            colors = GradientColors
        )
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(brush = brush, fontSize = 20.sp,
            lineHeight = 2.5.em,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    )


}
@Composable
fun SelectableText() {
    SelectionContainer {
        Text("This text is selectable")
    }
}
@Composable
fun PartiallySelectableText() {
    SelectionContainer {
        Column {
            Text("This text is selectable")
            Text("This one too")
            Text("This one as well")
            DisableSelection {
                Text("But not this one")
                Text("Neither this one")
            }
            Text("But again, you can select this one")
            Text("And this one too")
        }
    }
}

@Composable
fun SimpleClickableText(value: String,onClickAction:()->Unit) {
    ClickableText(
        text = AnnotatedString(value),
        onClick ={onClickAction()},
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            color = Color.Blue,
        )
    )
}

@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Click ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "URL",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)) {
            append("here")
        }

        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                  //  Log.d("Clicked URL", annotation.item)
                }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        maxLines = 1,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoLeadingZeroes() {
    /** does not start with 0 key */
    var input by rememberSaveable { mutableStateOf("") }
    TextField(
        value = input,
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
        }
    )
}



