package valentyn.androidtasks.models

import android.graphics.Color
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import valentyn.androidtasks.R
import valentyn.androidtasks.views.BaseContract

class Park(
    @PrimaryKey override var id: Long? = null,
    override var name: String = "",
    override var url: String = "",
    override var about: String = "",
    override var country: String = "",
    override var site: String = "",
    override var select: Boolean = false
) : BaseContract.Model {


    override fun getTextSelected(): Int {
        return if (this.select)
            R.string.button_selected
        else
            R.string.button_unselected
    }

    override fun getColorSelected(): Int {
        return if (this.select)
            Color.GREEN
        else
            Color.GRAY
    }
}





