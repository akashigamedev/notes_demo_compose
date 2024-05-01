package com.akashi.notesapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.akashi.notesapp.R
import com.akashi.notesapp.data.Note
import com.akashi.notesapp.presentation.viewmodels.NoteViewModel
import com.akashi.notesapp.utils.ui.theme.Black
import com.akashi.notesapp.utils.ui.theme.TextColor

@Composable
fun NoteDetailScreen(
    viewModel: NoteViewModel,
    navController: NavController,
    noteId: Int,
) {
    LaunchedEffect(true) {
        viewModel.getNote(noteId)
    }

    val note by viewModel.noteState.observeAsState()

    val id by remember { mutableIntStateOf(note?.id ?: -1) }
    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }
    val createdAt by remember { mutableLongStateOf(note?.createdAt ?: 0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    tint = Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable {

                        }
                )
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = Black,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable {
                            if (title.isNotBlank()) {
                                val newNote = Note(title, content, System.currentTimeMillis())
                                viewModel.upsertNote(newNote)
                                navController.popBackStack()
                            }
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Divider(thickness = 1.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = viewModel.getDateTime(createdAt),
            fontSize = 14.sp,
            color = TextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            text = title,
            setText = { newText -> title = newText },
            placeHolder = "Title",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            text = content,
            setText = { newText -> content = newText },
            placeHolder = "Start Typing...",
            color = TextColor
        )

    }
}