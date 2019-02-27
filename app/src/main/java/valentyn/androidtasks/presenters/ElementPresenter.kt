package valentyn.androidtasks.presenters

import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import valentyn.androidtasks.models.City
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.utils.StringUtils
import valentyn.androidtasks.utils.TextValidator
import valentyn.androidtasks.views.ElementContract
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.CityActivity
import valentyn.androidtasks.views.ParkActivity
import java.util.*
import android.net.Uri
import android.support.design.widget.TextInputLayout
import valentyn.androidtasks.R
import valentyn.androidtasks.utils.FileUtils

class ElementPresenter() : ElementContract.Presenter {

    private var view: ElementContract.View? = null
    var photoUri: Uri? = null
    var isChangeElement: Boolean = false

    override fun loadPhoto(url: String?) {
        view?.loadPhoto(url)
    }

    override fun updateSiteText(site: String?) {
        view?.updateSiteText(site)
    }

    override fun updateNameText(name: String?) {
        view?.updateNameText(name)
    }

    override fun updateDescriptionText(description: String?) {
        view?.updateDescriptionText(description)
    }

    override fun updateCountryText(country: String?) {
        view?.updateCountryText(country)
    }

    override fun updateTextSelectedButton(value: Int) {
        view?.updateTextSelectedButton(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        view?.updateColorSelectedButton(value)
    }

    override fun updateImageUri(uri: Uri?) {
        view?.updateImageUri(uri)
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

    fun updateSelectedButton(element: BaseContract.Model) {
        updateTextSelectedButton(element.getTextSelected())
        updateColorSelectedButton(element.getColorSelected())
    }

    override fun onAttach(view: ElementContract.View, id: String?, key: String) {
        this.view = view
        if (id != null && id.isNotEmpty()) {
            Single.just(RealmRepository.getRealmObject(id, key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BaseContract.Model?> {
                    override fun onSuccess(element: BaseContract.Model) {
                        loadPhoto(element.url)
                        updateSiteText(element.site)
                        updateNameText(element.name)
                        updateDescriptionText(element.about)
                        updateCountryText(element.country)
                        updateSelectedButton(element)
                    }

                    override fun onSubscribe(d: Disposable) {}
                    override fun onError(e: Throwable) {}
                }
                )
        }
    }

    fun setOnClickListenerSelectedButton(id: String?, key: String) {
        if (id != null && id.isNotEmpty()) {
            isChangeElement = true
            Single.just(RealmRepository.updateSelect(id, key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BaseContract.Model?> {
                    override fun onSuccess(element: BaseContract.Model) {
                        updateSelectedButton(element)
                    }

                    override fun onSubscribe(d: Disposable) {}
                    override fun onError(e: Throwable) {}

                })
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
        var element: BaseContract.Model? = null
        when (key) {
            CityActivity.CITY_KEY -> element = City(
                id = id ?: UUID.randomUUID().toString(),
                name = name,
                url = url,
                about = about,
                country = country,
                site = site,
                select = select == context.getString(R.string.button_selected)
            )
            ParkActivity.PARK_KEY -> element = Park(
                id = id ?: UUID.randomUUID().toString(),
                name = name,
                url = url,
                about = about,
                country = country,
                site = site,
                select = select == context.getString(R.string.button_selected)
            )
        }

        if (element != null) {
            Completable.fromAction { RealmRepository.save(element) }
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