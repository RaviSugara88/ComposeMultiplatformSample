package com.example.composemultiplateformex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composemultiplateformex.commonView.CustomOutlinedTextField

@Composable
fun LoginScree(){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Box (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){

        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(10.dp)) {

            CustomOutlinedTextField(
                value = email,
                onValueChange = {email=it},
                label = "Email",
                leadingIconImageVector = Icons.Default.Email,
                leadingIconDescription = "na",
                keyBoardOption = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                )
            )

          Spacer(modifier = Modifier.padding(top = 15.dp))

            CustomOutlinedTextField(
                value = password,
                onValueChange = {password=it},
                label = "Password",
                leadingIconImageVector = Icons.Default.Email,
                leadingIconDescription = "na",
                keyBoardOption = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {focusManager.clearFocus()}
                )
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))


        }

    }

}