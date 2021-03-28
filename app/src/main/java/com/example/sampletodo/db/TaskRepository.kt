package com.example.sampletodo.db

import androidx.lifecycle.LiveData
import com.example.sampletodo.data.Task

interface TaskRepository {

    fun getAlTasks() : LiveData<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

}
