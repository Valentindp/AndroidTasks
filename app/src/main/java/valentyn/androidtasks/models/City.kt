package valentyn.androidtasks.models

import android.graphics.Color
import valentyn.androidtasks.R
import java.io.Serializable

class City (cityId: Int,
                 cityName: String,
                 cityUrl: String,
                 cityAbout: String,
                 cityCountry: String,
                 citySite: String,
                 citySelect: Boolean) : Serializable
 {
     val id: Int = cityId
     val name: String = cityName
     val url: String = cityUrl
     var about: String = cityAbout
     var country: String = cityCountry
     var Site: String = citySite
     var Select: Boolean = citySelect

     fun getTextSelected():Int{
         if (this.Select){
             return R.string.button_selected
         }else{
            return R.string.button_unselected
         }
     }
     fun getColorSelected():Int {
         if (this.Select) {
             return Color.GREEN
         } else {
             return Color.GRAY
         }

     }
 }





