package com.akashi.notesapp.repository


import com.akashi.notesapp.data.Note
import com.akashi.notesapp.data.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    suspend fun getNote(id: Int): Note? {
        val notes = noteDao.getNote(id)
        return if(notes.isNotEmpty()) {
            notes[0]
        } else null
    }

    suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}