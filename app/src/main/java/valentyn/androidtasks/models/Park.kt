package valentyn.androidtasks.models

import android.graphics.Color
import valentyn.androidtasks.R
import java.io.Serializable

class Park (val id: Int,
            val name: String,
            val url: String,
            var about: String,
            var country: String,
            var site: String,
            var select: Boolean) : Serializable
 {

     fun getTextSelected():Int{
         if (this.select)
             return R.string.button_selected
         else
            return R.string.button_unselected

     }
     fun getColorSelected():Int {
         if (this.select)
             return Color.GREEN
          else
             return Color.GRAY
     }
 }





