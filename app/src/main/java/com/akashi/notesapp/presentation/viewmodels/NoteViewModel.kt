package com.akashi.notesapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashi.notesapp.data.Note
import com.akashi.notesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    var notes = mutableStateOf<List<Note>>(emptyList())
    private lateinit var _currentNote: Note
    private val _noteState = MutableLiveData<Note>()
    val noteState: LiveData<Note> get() = _noteState

    init {
        getAllNotes()
    }

    fun getNote(noteId: Int) {
        if(noteId ==  -1) {
            _currentNote = Note()
            _noteState.postValue(_currentNote)
        } else {
            viewModelScope.launch {
                _currentNote = noteRepository.getNote(noteId)!!
                viewModelScope.launch(Dispatchers.Main) {
                    _noteState.postValue(_currentNote)
                }
            }
        }

    }

    private fun getAllNotes() {
        viewModelScope.launch {
            notes.value = noteRepository.getAllNotes()
        }
    }

    fun upsertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.upsertNote(note)
            notes.value = noteRepository.getAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            notes.value = noteRepository.getAllNotes()
        }
    }

    fun getDateTime(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(milliseconds)
    }
}