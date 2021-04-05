package com.example.sampletodo.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampletodo.data.Task
import com.example.sampletodo.db.TaskRepository
import com.example.sampletodo.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepo: TaskRepository
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    var tasks : LiveData<List<Task>> = _tasks

    private val _taskState = MutableLiveData<TaskState>(TaskState.Default)
    val taskState : LiveData<TaskState> = _taskState

    val taskChecked = MutableLiveData<Boolean>()

    init {
        tasks = taskRepo.getAlTasks()
    }

    fun setCheckBox(isChecked: Boolean, task: Task) {
        Utils.logger("ViewModel", "Is Check changed")
        taskChecked.value = isChecked
        updateTask(task)
    }

    fun updateTask(task: Task) {
        Utils.logger("ViewModel", "Updating task")
        val checked = taskChecked.value
        Utils.logger("ViewModel", "value of checked is $checked")
        checked?.let {
                viewModelScope.launch {
                    Utils.logger("ViewModel", "Inside updating task")
                    val updateTask = Task(task.taskName, checked, task.id)
                    taskRepo.updateTask(updateTask)
                    tasks = taskRepo.getAlTasks()
            }
        }
    }

    fun cleanState() {
        _taskState.value = TaskState.Default
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            taskRepo.deleteTask(task)
            tasks = taskRepo.getAlTasks()
        }
    }
}