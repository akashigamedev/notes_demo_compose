package com.akashi.notesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.akashi.notesapp.data.Note
import com.akashi.notesapp.presentation.viewmodels.NoteViewModel
import com.akashi.notesapp.utils.ui.theme.Black
import com.akashi.notesapp.utils.ui.theme.Primary
import com.akashi.notesapp.utils.ui.theme.TextColor
import com.akashi.notesapp.utils.ui.theme.White

@Composable
fun NoteItem(
    viewModel: NoteViewModel,
    note: Note,
    navController: NavController
) {
    val date = viewModel.getDateTime(note.createdAt)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .clickable {
                navController.navigate("NoteDetailScreen/${note.id}")
            }
            .drawBehind {
                val borderSize = 10.dp.toPx()
                drawLine(
//                    color = Color(Random.nextLong(0xFFFFFFFF)),
                    color = Primary,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            }
            .padding(top = 20.dp)
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Column {

            Text(
                text = note.title,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Black
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                maxLines = 5,
                fontSize = 14.sp,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = date,
                fontSize = 14.sp,
                color = TextColor
            )

        }
    }
}