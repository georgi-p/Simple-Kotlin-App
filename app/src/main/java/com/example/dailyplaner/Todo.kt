package com.example.dailyplaner

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Todo.newInstance] factory method to
 * create an instance of this fragment.
 */
class Todo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        val taskInput: EditText = view.findViewById(R.id.taskInput)
        val addButton: Button = view.findViewById(R.id.addButton)
        val removeButton: Button = view.findViewById(R.id.removeButton)
        val taskDisplay: TextView = view.findViewById(R.id.taskDisplay)

        var isDialogEnabled = false

        val notificationSwitch: Switch = view.findViewById(R.id.notificationSwitch)
        notificationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            isDialogEnabled = isChecked
        }


        val tasks = mutableListOf<String>()

        addButton.setOnClickListener {
            val task = taskInput.text.toString()
            tasks.add(task)
            updateTaskDisplay(taskDisplay, tasks)
            taskInput.text.clear()

            if (isDialogEnabled) {
                val alertDialogBuilder = AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle("Task Added")
                alertDialogBuilder.setMessage("Task '$task' has been added.")
                alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }

            return@setOnClickListener
        }

        removeButton.setOnClickListener {
            val taskToRemove = taskInput.text.toString().trim()
            if (taskToRemove.isEmpty()) {
                if (tasks.isNotEmpty()) {
                    tasks.removeAt(tasks.size - 1)
                    updateTaskDisplay(taskDisplay, tasks)
                }
            } else {
                val iterator = tasks.iterator()
                while (iterator.hasNext()) {
                    val task = iterator.next()
                    if (task == taskToRemove || task != taskToRemove) {
                        iterator.remove()
                        updateTaskDisplay(taskDisplay, tasks)
                        taskInput.text.clear()
                        return@setOnClickListener
                    }
                }
            }
        }


        return view
    }

    private fun updateTaskDisplay(taskDisplay: TextView, tasks: List<String>) {
        val displayText = StringBuilder()
        tasks.forEachIndexed { index, task ->
            displayText.append("${index + 1}. $task\n")
        }
        taskDisplay.text = displayText.toString()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Todo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Todo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}