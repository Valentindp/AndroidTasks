package valentyn.androidtasks.models

import android.graphics.Color
import valentyn.androidtasks.R
import java.io.Serializable

class City(
    val id: Int,
    val name: String,
    val url: String,
    var about: String,
    var country: String,
    var site: String,
    var select: Boolean
) : Serializable {

    fun getTextSelected(): Int {
        return if (this.select)
            R.string.button_selected
        else
            R.string.button_unselected
    }

    fun getColorSelected(): Int {
        return if (this.select)
            Color.GREEN
        else
            Color.GRAY
    }
}





