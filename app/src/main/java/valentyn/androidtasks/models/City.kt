package valentyn.androidtasks.models

import android.graphics.Color
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import valentyn.androidtasks.R
import valentyn.androidtasks.views.BaseContract


class City(
    @PrimaryKey @Required override val id: Long,
    @Required override val name: String,
    override val url: String,
    override var about: String,
    override var country: String,
    override var site: String,
    override var select: Boolean
) : RealmObject(), BaseContract.Model {


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





