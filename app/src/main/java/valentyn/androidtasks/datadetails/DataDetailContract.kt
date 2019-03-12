package valentyn.androidtasks.datadetails

import android.net.Uri
import android.support.design.widget.TextInputLayout
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.utils.ErrorTypeTextValidate

interface DataDetailContract {

    interface View : BaseContract.View {

        fun setSiteText(site: String?)

        fun setNameText(name: String?)

        fun setDescriptionText(description: String?)

        fun setCountryText(country: String?)

        fun setTextSelectedButton(value: Int)

        fun setColorSelectedButton(value: Int)

        fun setPhoto(uri: Uri?)

        fun setUpTextChangeListeners()

        fun setUpOnClickListeners()

        fun setUpOnCreateContextMenuListener()

        fun getPhotoURI(): Uri?

        fun getPhotoIntent(uri: Uri?)

        fun getDrawingIntent()

        fun loadPhoto(url: String?)

        fun changeTextAndColorSelect()

        fun showEmptyError()

        fun showValidateError(textView: TextInputLayout, errorType: ErrorTypeTextValidate? = null)

        fun onFinish()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View, dataId: String?, key: String) {}

        fun onLoaded(data: BaseContract.Data)

        fun onNotAvailable()

        fun selectData()

        fun getPhoto()

        fun getPicture()

        fun result(requestCode: Int, resultCode: Int)

    }
}