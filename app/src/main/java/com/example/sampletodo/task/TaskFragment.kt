package com.example.sampletodo.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampletodo.adapter.TaskAdapter
import com.example.sampletodo.data.Task
import com.example.sampletodo.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var viewModel: TaskViewModel

    private val diffList = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
    }

    private fun initAdapter() {
        initDummyList()
        binding.tasks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val taskAdapter = TaskAdapter()
            taskAdapter.taskList = diffList
            adapter = taskAdapter
        }
    }

    private fun initDummyList() {
        val task1 = Task("Dummy 1", false)
        val task2 = Task("Dummy 2", false)
        val task3 = Task("Dummy 3", false)
        val task4 = Task("Dummy 4", false)
        val task5 = Task("Dummy 5", false)
        val task6 = Task("Dummy 6", false)
        val task7 = Task("Dummy 7", false)
        diffList.add(task1)
        diffList.add(task2)
        diffList.add(task3)
        diffList.add(task4)
        diffList.add(task5)
        diffList.add(task6)
        diffList.add(task7)
        diffList.add(task2)
        diffList.add(task3)
        diffList.add(task4)
        diffList.add(task5)
        diffList.add(task6)
        diffList.add(task7)
    }

}