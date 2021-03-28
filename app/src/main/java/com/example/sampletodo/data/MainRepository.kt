package com.example.sampletodo.data

import androidx.lifecycle.LiveData
import com.example.sampletodo.db.TaskDao
import com.example.sampletodo.db.TaskRepository
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getAlTasks(): LiveData<List<Task>> = taskDao.getAlTasks()

    override suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}