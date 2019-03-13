package valentyn.androidtasks.datadetails

import android.app.Activity
import android.content.Intent
import android.text.Editable
import valentyn.androidtasks.data.City
import valentyn.androidtasks.data.Park
import valentyn.androidtasks.data.source.repository.DataRepository
import valentyn.androidtasks.utils.StringUtils
import valentyn.androidtasks.utils.TextValidator
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.datadetails.parks.ParkActivity
import android.net.Uri
import android.support.design.widget.TextInputLayout
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.graphics.FingerPaint
import valentyn.androidtasks.utils.ErrorTypeTextValidate
import java.lang.RuntimeException

class DataDetailPresenter() : DataDetailContract.Presenter {

    private var view: DataDetailContract.View? = null
    private var dataId: String? = null
    private var key = ""
    private var photoUri: Uri? = null

    var isDataChange = false

    val REQUEST_TAKE_PHOTO: Int = 1
    val REQUEST_TAKE_DRAWING: Int = 2

    override fun onAttach(view: DataDetailContract.View, dataId: String?, key: String) {
        this.view = view
        this.dataId = dataId
        this.key = key

        this.view?.apply {
            setUpTextChangeListeners()
            setUpOnClickListeners()
            setUpOnCreateContextMenuListener()
        }
    }

    override fun start() {

        if (isNewTask()) return

        DataRepository.getData(key, dataId, object : DataSource.GetDataCallback {
            override fun onDataLoaded(data: BaseContract.Data) {
                onLoaded(data)
            }

            override fun onDataNotAvailable() {
                onNotAvailable()
            }
        })
    }

    override fun onLoaded(data: BaseContract.Data) {
        view?.apply {
            setSiteText(data.site)
            setNameText(data.title)
            setDescriptionText(data.description)
            setCountryText(data.country)
            setTextSelectedButton(data.getTextSelected())
            setColorSelectedButton(data.getColorSelected())
            setImage(data.url)
        }
    }

    override fun onNotAvailable() {
        view?.showEmptyError()
    }

    override fun selectData() {
        if (!isNewTask()) {
            isDataChange = true
            DataRepository.updateSelect(dataId)
        } else {
            /*  In this case, the record in the database does not occur. The form of the new element is open. */
        }
        view?.changeTextAndColorSelect()
    }


    fun getTextValidator(textView: TextInputLayout): TextValidator {
        return object : TextValidator(textView) {
            override fun validate(textView: TextInputLayout, s: Editable) {
                var haveForbiddenCharacter = false
                s.forEachIndexed { index, c ->
                    if (StringUtils.haveForbiddenCharacter(c)) {
                        s.replace(index, index + 1, "")
                        haveForbiddenCharacter = true
                    }
                }
                if (haveForbiddenCharacter) {
                    view?.showValidateError(textView, ErrorTypeTextValidate.ERROR_FORBIDDEN_CHARACTER)
                } else {
                    if (s.length == textView.counterMaxLength && !haveForbiddenCharacter) {
                        view?.showValidateError(textView, ErrorTypeTextValidate.ERROR_MAX_LENGTH)
                    } else {
                        view?.showValidateError(textView)
                    }
                }
            }
        }
    }

    override fun getPhoto() {
        photoUri = view?.getPhotoURI()
        view?.getPhotoIntent(photoUri)
    }

    override fun getPicture() {
        view?.getDrawingIntent()
    }

    fun saveData(name: String, url: String, description: String, country: String, site: String, select: Boolean) {
        isDataChange = true
        if (isNewTask()) {
            createData(name, url, description, country, site, select)
        } else {
            updateData(name, url, description, country, site, select)
        }
    }

    private fun isNewTask(): Boolean = dataId == null

    private fun createData(
        name: String,
        url: String,
        description: String,
        country: String,
        site: String,
        select: Boolean
    ) {
        var newData: BaseContract.Data? = null
        when (key) {
            CityActivity.CITY_KEY -> newData = City(
                title = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
            ParkActivity.PARK_KEY -> newData = Park(
                title = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
        }
        if (newData != null) {
            if (newData.isEmpty()) {
                view?.showEmptyError()
            } else {
                DataRepository.save(newData)
                view?.onFinish()
            }
        } else {
            throw RuntimeException("Object key is empty.")
        }
    }

    private fun updateData(
        name: String,
        url: String,
        description: String,
        country: String,
        site: String,
        select: Boolean
    ) {
        if (isNewTask()) {
            throw RuntimeException("updateData() was called but data is new.")
        }

        var data: BaseContract.Data? = null
        when (key) {
            CityActivity.CITY_KEY -> data = City(
                id = dataId!!,
                title = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
            ParkActivity.PARK_KEY -> data = Park(
                id = dataId!!,
                title = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
        }
        if (data != null) {
            DataRepository.save(data)
            view?.onFinish()
        } else {
            throw RuntimeException("Object key is empty.")
        }
    }

    override fun result(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_TAKE_PHOTO -> {
                    view?.setImage(photoUri.toString())
                }
                REQUEST_TAKE_DRAWING -> {
                    val drawingUri = data?.getStringExtra(FingerPaint.FILE_DRAWING_URI)
                    view?.setImage(drawingUri)
                }
            }
        }
    }

    override fun onDetach() {
        view = null
    }
}