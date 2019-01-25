package valentyn.androidtasks.views

interface BaseContract {

    interface Presenter<in T, E, F> {
        fun onAttach(view: T, id: E, key : F)
        fun onDetach()
    }

    interface View {

    }

    interface Model  {
        val id: Long
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