package com.example.sampletodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val taskName: String,
        val taskProgress: Boolean = false
)