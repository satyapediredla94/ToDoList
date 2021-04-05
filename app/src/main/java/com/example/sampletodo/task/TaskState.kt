package com.example.sampletodo.task

sealed class TaskState {

    object Default : TaskState()
    object Success : TaskState()
    data class Failed(val errorMessage: String) : TaskState()
    object Update : TaskState()

}
