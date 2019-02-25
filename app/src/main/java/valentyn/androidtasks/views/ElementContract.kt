package valentyn.androidtasks.views

import android.content.Context
import android.net.Uri

interface ElementContract {

    interface View : BaseContract.View {

        fun updateSiteText(site: String?)

        fun updateNameText(name: String?)

        fun updateDescriptionText(description: String?)

        fun updateCountryText(country: String?)

        fun updateTextSelectedButton(value: Int)

        fun updateColorSelectedButton(value: Int)

        fun loadPhoto(url: String?)

        fun getContextView(): Context

        fun updateImageUri(uri:Uri?)

    }

    interface Presenter : BaseContract.Presenter<ElementContract.View, String?, String> {

        fun updateSiteText(site: String?)

        fun updateNameText(name: String?)

        fun updateDescriptionText(description: String?)

        fun updateCountryText(country: String?)

        fun updateTextSelectedButton(value: Int)

        fun updateColorSelectedButton(value: Int)

        fun loadPhoto(url: String?)

        fun updateImageUri(uri:Uri?)

    }
}