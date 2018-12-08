package valentyn.androidtasks.models

import java.io.Serializable

data class City (val id: Int,
                 val name: String,
                 val url: String,
                 var about: String,
                 var country: String,
                 var Site: String,
                 var Select: Boolean) :Serializable

