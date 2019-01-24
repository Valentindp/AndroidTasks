package valentyn.androidtasks.views

import android.os.Parcelable

interface BaseContract {

    interface Presenter<in T,E> {
        fun onAttach(view: T, model: E)
        fun onDetach()
    }

    interface View {

    }

    interface Model : Parcelable {
        val id: Int
        val name: String
        val url: String
        var about: String
        var country: String
        var site: String
        var select: Boolean

        fun getTextSelected(): Int

        fun getColorSelected(): Int
    }

}