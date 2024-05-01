package com.akashi.notesapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    text: String,
    setText: (newText: String) -> Unit,
    placeHolder: String = "",
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp
) {
    BasicTextField(
        value = text,
        onValueChange = {setText(it)},
        decorationBox = {innerTextField ->
            Box(
            ) {
                if(text.isEmpty()) {
                    Text(
                        text = placeHolder,
                        color = Color.Gray,
                        fontWeight = fontWeight,
                        fontSize = fontSize
                    )
                }
                innerTextField()
            }
        },
        textStyle = TextStyle(
            color = color,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
    )
}