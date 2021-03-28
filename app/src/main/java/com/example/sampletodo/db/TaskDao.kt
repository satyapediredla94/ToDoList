package com.example.sampletodo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampletodo.data.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAlTasks() : LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}
