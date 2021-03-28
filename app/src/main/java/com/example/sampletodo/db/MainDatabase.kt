package com.example.sampletodo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampletodo.data.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}
