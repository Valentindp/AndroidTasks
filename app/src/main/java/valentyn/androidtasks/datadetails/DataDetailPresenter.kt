package valentyn.androidtasks.datadetails

import android.content.Context
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
import valentyn.androidtasks.utils.FileUtils
import java.lang.RuntimeException

class DataDetailPresenter() : DataDetailContract.Presenter {

    private var view: DataDetailContract.View? = null
    private var dataId: String? = null
    private var key = ""

    var photoUri: Uri? = null
    var isDataChange = false

    override fun onAttach(view: DataDetailContract.View, dataId: String?, key: String) {
        this.view = view
        this.dataId = dataId
        this.key = key
    }

    override fun start() {

        if (dataId == null) return

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
        view?.setSiteText(data.site)
        view?.setNameText(data.name)
        view?.setDescriptionText(data.description)
        view?.setCountryText(data.country)
        view?.setTextSelectedButton(data.getTextSelected())
        view?.setColorSelectedButton(data.getColorSelected())
        view?.loadPhoto(data.url)
    }

    override fun onNotAvailable() {
        view?.showEmptyDataError()
    }

    override fun selectData() {
        if (dataId != null) {
            isDataChange = true
            DataRepository.updateSelect(dataId)
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
                if (haveForbiddenCharacter) textView.error = "This symbol is not allowed!" else {
                    textView.error =
                        if (s.length == textView.counterMaxLength) "Maximum number of characters reached!" else null
                }
            }
        }
    }


    fun getPhotoURI(context: Context): Uri? {
        photoUri = FileUtils.getPhotoURI(context)
        return photoUri
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
                name = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
            ParkActivity.PARK_KEY -> newData = Park(
                name = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
        }
        if (newData != null) {
            if (newData.isEmpty()) {
                view?.showEmptyDataError()
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
                name = name,
                url = url,
                description = description,
                country = country,
                site = site,
                select = select
            )
            ParkActivity.PARK_KEY -> data = Park(
                id = dataId!!,
                name = name,
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

    override fun onDetach() {
        view = null
    }

}