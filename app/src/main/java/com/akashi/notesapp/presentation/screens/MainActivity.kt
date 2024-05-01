package com.akashi.notesapp.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akashi.notesapp.presentation.ui.NoteDetailScreen
import com.akashi.notesapp.presentation.ui.NotesScreen
import com.akashi.notesapp.presentation.viewmodels.NoteViewModel
import com.akashi.notesapp.utils.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "NotesScreen") {
                    composable("NotesScreen") {
                        NotesScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("NoteDetailScreen/{noteId}") {backStackEntry ->
                        val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: -1
                        NoteDetailScreen(viewModel, navController = navController, noteId)
                    }
                }
            }
        }
    }
}