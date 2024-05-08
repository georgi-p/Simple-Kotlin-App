package com.example.dailyplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.BufferedReader
import java.io.InputStreamReader
import android.widget.TextView
import com.example.dailyplaner.R

data class NoteItem(val title: String, val category: String, val date: String, val location: String) {
    override fun toString(): String = "$title - $category on $date at $location"
}

class Notes : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val notesList = readFileFromAssets("notes.txt")
        val notesAdapter = NotesAdapter(notesList)
        recyclerView.adapter = notesAdapter
        return view
    }

    private fun readFileFromAssets(fileName: String): List<NoteItem> {
        val inputStream = requireContext().assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val noteList = mutableListOf<NoteItem>()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val parts = line?.split(",")?.map { it.trim() }
            if (parts != null && parts.size == 4) {
                val note = NoteItem(parts[0], parts[1], parts[2], parts[3])
                noteList.add(note)
            }
        }
        reader.close()
        return noteList
    }

    class NotesAdapter(private val notesList: List<NoteItem>) :
        RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
            return NotesViewHolder(view)
        }

        override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
            holder.bind(notesList[position])
        }

        override fun getItemCount(): Int = notesList.size

        inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)

            fun bind(note: NoteItem) {
                noteTextView.text = note.toString()
            }
        }
    }
}
