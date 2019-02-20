package valentyn.androidtasks.views


interface ElementContract {

    interface View : BaseContract.View {

        fun updateSiteText(site: String?)

        fun updateNameText(name: String?)

        fun updateDescriptionText(description: String?)

        fun updateCountryText(country: String?)

        fun updateTextSelectedButton(value: Int)

        fun updateColorSelectedButton(value: Int)

        fun loadPhoto(url: String?)

    }

    interface Presenter : BaseContract.Presenter<ElementContract.View, Long, String> {

        fun updateSiteText(site: String?)

        fun updateNameText(name: String?)

        fun updateDescriptionText(description: String?)

        fun updateCountryText(country: String?)

        fun updateTextSelectedButton(value: Int)

        fun updateColorSelectedButton(value: Int)

        fun loadPhoto(url: String?)

    }
}