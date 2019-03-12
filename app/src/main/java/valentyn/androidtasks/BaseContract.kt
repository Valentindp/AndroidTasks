package valentyn.androidtasks

import android.graphics.Color
import io.realm.RealmModel


interface BaseContract {

    interface Presenter<T> {

        fun onDetach()

        fun start()
    }

    interface View {}

    interface Data : RealmModel {
        val id: String
        var title: String
        var url: String
        var description: String
        var country: String
        var site: String
        var select: Boolean

        fun getTextSelected(): Int =  if (select) R.string.button_selected else R.string.button_unselected

        fun getColorSelected(): Int = if (select) Color.GREEN else Color.GRAY

        fun isEmpty():Boolean = title.isEmpty()
    }

}