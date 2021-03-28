package com.example.sampletodo.task

import androidx.lifecycle.ViewModel
import com.example.sampletodo.db.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    taskRepo: TaskRepository
) : ViewModel() {
}