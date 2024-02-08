package com.example.flashcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun backButton(onClick:()->Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription ="Back Arrow",
            modifier = Modifier
                .size(80.dp)
                .background(Color.White))
    }
}
@Composable
fun stylesButtton(onClick1: () -> Unit, string: String) {
    Button(
        onClick = { onClick1() },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Yellow)
    ) {
        Text(
            text = string,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun textfield(txt:String, onvalueChange:(String)-> Unit, label: @Composable ()-> Unit
) {
    TextField(
        value = txt,
        onValueChange ={onvalueChange(it)},
        label=label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        )
}
//@Preview(showBackground = true)
//@Composable
//fun pre() {
//    var text by remember{ mutableStateOf("") }
//    textfield(txt = text, onvalueChange = {text=it}) {
//        Text(text = "enter text")
//    }
//}