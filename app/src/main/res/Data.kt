import android.content.Context
import com.example.dailyplaner.Notes
import com.example.dailyplanner.Notes
import java.io.IOException
import java.util.Date

data class Data(val activity:String, val type:String, val date:Date, val place:String)

fun readNotesFromAssets(context: Context): List<Notes> {
    val notes = mutableListOf<Notes>()
    try {
        context.assets.open("notes.txt").bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val parts = line.split(',')
                if (parts.size == 4) {
                    notes.add(Notes(parts[0], parts[1], parts[2], parts[3]))
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return notes
}

