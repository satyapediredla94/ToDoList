package com.example.sampletodo.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletodo.R
import com.example.sampletodo.data.Task
import com.example.sampletodo.databinding.TaskItemBinding
import com.example.sampletodo.task.TaskViewModel
import com.example.sampletodo.utils.AppConstants
import com.example.sampletodo.utils.Utils

class TaskAdapter(
        private val viewModel: TaskViewModel
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()) {

    class TaskViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task, viewModel: TaskViewModel) {
            binding.task = item
            binding.viewModel = viewModel
            binding.taskBackground.setBackgroundColor(getBackgroundColors())
            binding.executePendingBindings()
        }

        private fun getBackgroundColors() : Int {
            return Color.CYAN
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, viewModel)
        Utils.logger("Task Item", """
            taskName : ${task.taskName}
            taskProgress: ${task.taskProgress}
            taskId: ${task.id}
        """.trimIndent())
        holder.itemView.setOnClickListener {
            val taskId = task.id
            val taskName = task.taskName
            val taskProgress = task.taskProgress
            val bundle = Bundle()
            bundle.putInt(AppConstants.TASK_ID, taskId!!)
            bundle.putString(AppConstants.TASK_NAME, taskName)
            bundle.putBoolean(AppConstants.TASK_PROGRESS, taskProgress)
            it.findNavController().navigate(R.id.addEditTaskFragment, bundle)
        }
    }

    class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
}