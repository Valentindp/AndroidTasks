package valentyn.androidtasks.data

import android.graphics.Color
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import valentyn.androidtasks.R
import valentyn.androidtasks.BaseContract
import java.util.*

open class Park(
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    override var name: String = "",
    override var url: String = "",
    override var description: String = "",
    override var country: String = "",
    override var site: String = "",
    override var select: Boolean = false
) : BaseContract.Data, RealmObject() {

    override fun getTextSelected(): Int = if (this.select) R.string.button_selected else R.string.button_unselected

    override fun getColorSelected(): Int = if (this.select) Color.GREEN else Color.GRAY

}





