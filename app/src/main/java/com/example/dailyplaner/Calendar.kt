package com.example.dailyplaner

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Calendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class Calendar : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val dateTV: TextView = view.findViewById(R.id.idTVDate)
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        val btnAddEvent: Button = view.findViewById(R.id.btnAddEvent)


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$dayOfMonth-${month + 1}-$year"
            dateTV.text = date
        }

        fun startTodoFragment() {
            val dlg = AlertDialog.Builder(requireContext())
            dlg.setTitle("Event")
            dlg.setMessage("Are you sure you want to add an event!")
            dlg.setPositiveButton("Ok") { dialog, which ->
                val todoFragment = Todo()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, todoFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            dlg.setNegativeButton("Cancel") {dialog, which ->}
            dlg.show()
        }

        btnAddEvent.setOnClickListener {
            startTodoFragment()
        }




        return view


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Calendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Calendar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
