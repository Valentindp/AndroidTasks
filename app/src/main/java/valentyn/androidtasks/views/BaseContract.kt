package valentyn.androidtasks.views

interface BaseContract {

    interface Presenter<in T> {
        fun onAttach(view: T)
    }

    interface View {

    }
}