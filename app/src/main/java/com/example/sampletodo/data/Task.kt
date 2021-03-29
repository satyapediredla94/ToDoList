package com.example.sampletodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
        val taskName: String,
        val taskProgress: Boolean = false,
        @PrimaryKey(autoGenerate = true)
        var id: Int?= -1
)