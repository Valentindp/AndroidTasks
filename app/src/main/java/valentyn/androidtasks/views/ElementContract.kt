package valentyn.androidtasks.views

interface ElementContract {

    interface View : BaseContract.View {

        fun setOnClickListenerSelectedButton()

        fun updateSiteTextView(site: String)

        fun updateNameTextView(name: String)

        fun updateDescriptionTextView(description: String)

        fun updateCountryTextView(country: String)

        fun updateTextSelectedButton(value: Int)

        fun updateColorSelectedButton(value: Int)

        fun loadPhoto(url: String)
    }

    interface Presenter : BaseContract.Presenter<ElementContract.View> {

        fun setOnClickListenerSelectedButton()

        fun updateSiteTextView()

        fun updateNameTextView()

        fun updateDescriptionTextView()

        fun updateCountryTextView()

        fun updateTextSelectedButton()

        fun updateColorSelectedButton()

        fun loadPhoto()

        fun init()

        fun onFinish()
    }
}