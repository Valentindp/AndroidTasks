package valentyn.androidtasks.datadetails

import android.net.Uri
import valentyn.androidtasks.BaseContract

interface DataDetailContract {

    interface View : BaseContract.View {

        fun setSiteText(site: String?)

        fun setNameText(name: String?)

        fun setDescriptionText(description: String?)

        fun setCountryText(country: String?)

        fun setTextSelectedButton(value: Int)

        fun setColorSelectedButton(value: Int)

        fun setImageUri(uri:Uri?)

        fun loadPhoto(url: String?)

        fun changeTextAndColorSelect()

        fun showEmptyDataError()

        fun onFinish()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View, dataId: String?, key: String){}

        fun onLoaded(data : BaseContract.Data)

        fun onNotAvailable()

        fun selectData()

    }
}