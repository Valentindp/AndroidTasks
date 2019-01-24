package valentyn.androidtasks.presenters

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.models.City
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.CityActivity
import valentyn.androidtasks.views.ElementContract
import valentyn.androidtasks.views.MainActivity

class ElementPresenter() : ElementContract.Presenter {

    private lateinit var view: ElementContract.View
    lateinit var element: BaseContract.Model

    override fun loadPhoto() {
        view.loadPhoto(element.url)
    }

    override fun updateSiteText() {
        view.updateSiteText(element.site)
    }

    override fun updateNameText() {
        view.updateNameText(element.name)
    }

    override fun updateDescriptionText() {
        view.updateDescriptionText(element.about)
    }

    override fun updateCountryText() {
        view.updateCountryText(element.country)
    }

    override fun updateTextSelectedButton() {
        view.updateTextSelectedButton(element.getTextSelected())
    }

    override fun updateColorSelectedButton() {
        view.updateColorSelectedButton(element.getColorSelected())
    }

    override fun onAttach(view: ElementContract.View, model: BaseContract.Model) {
        this.view = view
        this.element = model
        init()
    }

    fun setOnClickListenerSelectedButton() {
        element.select = !element.select
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

}