package com.akashi.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    var title: String = "",
    var content: String = "",
    var createdAt: Long = 0,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
