package valentyn.androidtasks.presenters

import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.utils.StringUtils
import valentyn.androidtasks.views.ElementContract

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

    override fun getNameTextError(text: String): String = StringUtils.checkString(text)

    override fun onAttach(view: ElementContract.View, id: Long?, key: String) {
        this.view = view
        if (id != null && id > 0) {
            isNewElement = false
            val element = RealmRepository.getRealmObject(id, key)
            if (element != null) {
                loadPhoto(element.url)
                updateSiteText(element.site)
                updateNameText(element.name)
                updateDescriptionText(element.about)
                updateCountryText(element.country)
                updateTextSelectedButton(element.getTextSelected())
                updateColorSelectedButton(element.getColorSelected())
            }
        }
    }

    fun setOnClickListenerSelectedButton(id: Long?, key: String) {
        if (!isNewElement) {
            isChangeElement = true
            val element = RealmRepository.getRealmObject(id, key)
            RealmRepository.updateSelect(element)
            if (element != null) {
                updateTextSelectedButton(element.getTextSelected())
                updateColorSelectedButton(element.getColorSelected())
            }
        }
    }

    override fun onDetach() {
        view = null
    }

}