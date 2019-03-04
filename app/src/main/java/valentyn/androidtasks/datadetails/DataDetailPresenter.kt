package valentyn.androidtasks.datadetails

import android.content.Context
import android.text.Editable
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import valentyn.androidtasks.data.City
import valentyn.androidtasks.data.Park
import valentyn.androidtasks.data.source.repository.DataRepository
import valentyn.androidtasks.utils.StringUtils
import valentyn.androidtasks.utils.TextValidator
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.datadetails.parks.ParkActivity
import java.util.*
import android.net.Uri
import android.support.design.widget.TextInputLayout
import valentyn.androidtasks.R
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.utils.FileUtils

class DataDetailPresenter() : DataDetailContract.Presenter {

    private var view: DataDetailContract.View? = null
    private var dataId: String? = null
    private var key: String = ""
    var photoUri: Uri? = null
    var isChangeElement: Boolean = false

    override fun onAttach(view: DataDetailContract.View, dataId: String, key: String) {
        this.view = view
        this.dataId = dataId
        this.key = key
    }

    override fun start() {
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
            isChangeElement = true
            DataRepository.updateSelect(dataId)
            view?.changeTextAndColorSelect()
        }
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

    fun saveElement(
        context: Context,
        id: String?,
        name: String,
        url: String,
        about: String,
        country: String,
        site: String,
        select: String,
        key: String
    ) {
        var element: BaseContract.Data? = null
        when (key) {
            CityActivity.CITY_KEY -> element = City(
                id = id ?: UUID.randomUUID().toString(),
                name = name,
                url = url,
                description = about,
                country = country,
                site = site,
                select = select == context.getString(R.string.button_selected)
            )
            ParkActivity.PARK_KEY -> element = Park(
                id = id ?: UUID.randomUUID().toString(),
                name = name,
                url = url,
                description = about,
                country = country,
                site = site,
                select = select == context.getString(R.string.button_selected)
            )
        }

        if (element != null) {
            Completable.fromAction { DataRepository.save(element) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {
                        isChangeElement = true
                        view?.onFinish()
                    }

                    override fun onSubscribe(d: Disposable) {}
                    override fun onError(e: Throwable) {}
                })
        }
    }


    override fun onDetach() {
        view = null
    }

}