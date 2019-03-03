package valentyn.androidtasks

import io.realm.RealmModel


interface BaseContract {

    interface Presenter<T> {
        fun onDetach()
        fun start()
    }

    interface View {}

    interface Data : RealmModel {
        val id: String
        var name: String
        var url: String
        var description: String
        var country: String
        var site: String
        var select: Boolean

        fun getTextSelected(): Int

        fun getColorSelected(): Int
    }

}