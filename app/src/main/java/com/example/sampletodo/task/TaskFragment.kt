package com.example.sampletodo.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletodo.adapter.TaskAdapter
import com.example.sampletodo.databinding.FragmentTaskBinding
import com.example.sampletodo.utils.Utils

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var viewModel: TaskViewModel

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
        requireActivity().onBackPressed()
        initAdapter()
        setClickListeners()
    }

    private fun initAdapter() {
        binding.tasks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TaskAdapter(viewModel)
            val itemTouchHelperCallback =
                    object :
                            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                        override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                        ): Boolean {

                            return false
                        }

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            val task = (adapter as TaskAdapter).currentList[viewHolder.adapterPosition]
                            viewModel.delete(task)
                            Toast.makeText(
                                    requireContext(),
                                    "Deleted task at ${viewHolder.adapterPosition + 1} position",
                                    Toast.LENGTH_SHORT
                            ).show()

                            Utils.logger("Swiped:", task.toString())
                        }

                    }
            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun setClickListeners() {
        binding.fab.setOnClickListener {
            Log.e("Task Fragment", "fab clicked")
            val action = TaskFragmentDirections.actionTaskFragmentToAddEditTaskFragment("")
            findNavController().navigate(action)
        }
    }



}