package valentyn.androidtasks.presenters

import android.text.InputFilter
import android.widget.TextView
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.utils.StringUtils
import valentyn.androidtasks.utils.TextValidator
import valentyn.androidtasks.views.ElementContract
import valentyn.androidtasks.views.BaseContract

class ElementPresenter() : ElementContract.Presenter {

    private var view: ElementContract.View? = null
    var isChangeElement: Boolean = false
    var isNewElement: Boolean = true

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

    fun getTextValidator(textView: TextView): TextValidator {
        return object : TextValidator(textView) {
            override fun validate(textView: TextView, text: String) {
                val textError = StringUtils.checkString(text)
                textView.error = if (textError.isEmpty()) null else textError
            }
        }
    }

    fun getInputFilters(): Array<InputFilter> =
        arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            if (source != null && StringUtils.forbiddenCharacterString.contains(source)) "" else null
        })

    fun updateSelectedButton(element: BaseContract.Model) {
        updateTextSelectedButton(element.getTextSelected())
        updateColorSelectedButton(element.getColorSelected())
    }

    override fun onAttach(view: ElementContract.View, id: Long?, key: String) {
        this.view = view
        if (id != null && id > 0) {
            isNewElement = false
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


    fun setOnClickListenerSelectedButton(id: Long?, key: String) {
        if (!isNewElement) {
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

    override fun onDetach() {
        view = null
    }

}