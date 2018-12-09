package valentyn.androidtasks.models

import android.graphics.Color
import valentyn.androidtasks.R
import java.io.Serializable

class City (CityId: Int,
                 CityName: String,
                 CityUrl: String,
                 CityAbout: String,
                 CityCountry: String,
                 CitySite: String,
                   CitySelect: Boolean) : Serializable
 {
     val id: Int = CityId
     val name: String = CityName
     val url: String = CityUrl
     var about: String = CityAbout
     var country: String = CityCountry
     var Site: String = CitySite
     var Select: Boolean = CitySelect

     fun getTextSelected():Int{
         if (this.Select){
             return R.string.button_selected_name1
         }else{
            return R.string.button_selected_name2
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





