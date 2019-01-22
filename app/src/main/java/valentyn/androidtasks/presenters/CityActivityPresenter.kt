package valentyn.androidtasks.presenters

import valentyn.androidtasks.models.City
import valentyn.androidtasks.views.ElementContract

class CityActivityPresenter() : ElementContract.Presenter {

    private lateinit var cityActivity: ElementContract.View
    lateinit var city: City

    override fun loadPhoto() {
        cityActivity.loadPhoto(city.url)
    }

    override fun updateSiteTextView() {
        cityActivity.updateSiteTextView(city.site)
    }

    override fun updateNameTextView() {
        cityActivity.updateNameTextView(city.name)
    }

    override fun updateDescriptionTextView() {
        cityActivity.updateDescriptionTextView(city.about)
    }

    override fun updateCountryTextView() {
        cityActivity.updateCountryTextView(city.country)
    }

    override fun updateTextSelectedButton() {
        cityActivity.updateTextSelectedButton(city.getTextSelected())
    }

    override fun updateColorSelectedButton() {
        cityActivity.updateColorSelectedButton(city.getColorSelected())
    }

    override fun onAttach(view: ElementContract.View) {
        cityActivity = view
    }

    override fun setOnClickListenerSelectedButton() {
        city.select = !city.select
        updateTextSelectedButton()
        updateColorSelectedButton()
    }

    fun init() {
        loadPhoto()
        updateTextSelectedButton()
        updateColorSelectedButton()
        updateSiteTextView()
        updateNameTextView()
        updateDescriptionTextView()
        updateCountryTextView()
        cityActivity.setOnClickListenerSelectedButton()
    }


}