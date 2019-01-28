package valentyn.androidtasks.views

import io.realm.RealmModel

interface BaseContract {

    interface Presenter<in T, E, F> {
        fun onAttach(view: T, id: E?, key : F)
        fun onDetach()
    }

    interface View {

    }

    interface Model: RealmModel  {
        var id: Long?
        var name: String
        var url: String
        var about: String
        var country: String
        var site: String
        var select: Boolean

        fun getTextSelected(): Int

        fun getColorSelected(): Int
    }

}