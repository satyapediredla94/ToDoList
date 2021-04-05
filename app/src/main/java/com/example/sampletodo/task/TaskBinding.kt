package com.example.sampletodo.task

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletodo.adapter.TaskAdapter
import com.example.sampletodo.data.Task

@BindingAdapter("app:setItems")
fun setItems(tasks: RecyclerView, items: List<Task>?) {
    items?.let {
        (tasks.adapter as TaskAdapter).submitList(it)
    }
}