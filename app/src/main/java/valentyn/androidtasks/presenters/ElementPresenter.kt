package valentyn.androidtasks.presenters

import valentyn.androidtasks.models.City
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.ElementContract

class ElementPresenter() : ElementContract.Presenter {

    private var view: ElementContract.View? = null
    private var element: BaseContract.Model? = null
    var isChangeElement: Boolean = false

    override fun loadPhoto() {
        view?.loadPhoto(element?.url)
    }

    override fun updateSiteText() {
        view?.updateSiteText(element?.site)
    }

    override fun updateNameText() {
        view?.updateNameText(element?.name)
    }

    override fun updateDescriptionText() {
        view?.updateDescriptionText(element?.about)
    }

    override fun updateCountryText() {
        view?.updateCountryText(element?.country)
    }

    override fun updateTextSelectedButton() {
        view?.updateTextSelectedButton(element?.getTextSelected()!!)
    }

    override fun updateColorSelectedButton() {
        view?.updateColorSelectedButton(element?.getColorSelected()!!)
    }

    override fun onAttach(view: ElementContract.View, id: Long?, key: String) {
        this.view = view
        this.element = RealmRepository.getRealmObject(id, key)
        init()
    }

    fun setOnClickListenerSelectedButton() {
        when (element) {
            is City -> RealmRepository.updateSelect(element?.id, "CITY_KEY")
            is Park -> RealmRepository.updateSelect(element?.id, "PARK_KEY")
        }
        isChangeElement = true
        updateTextSelectedButton()
        updateColorSelectedButton()
    }

    override fun init() {
        loadPhoto()
        updateTextSelectedButton()
        updateColorSelectedButton()
        updateSiteText()
        updateNameText()
        updateDescriptionText()
        updateCountryText()
    }

    override fun onDetach() {
        view = null
    }

}