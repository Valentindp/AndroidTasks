package valentyn.androidtasks.presenters

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.models.City
import valentyn.androidtasks.views.CityActivity
import valentyn.androidtasks.views.ElementContract
import valentyn.androidtasks.views.MainActivity

class CityActivityPresenter() : ElementContract.Presenter {

    private lateinit var view: ElementContract.View
    private lateinit var element: City

    override fun loadPhoto() {
        view.loadPhoto(element.url)
    }

    override fun updateSiteTextView() {
        view.updateSiteTextView(element.site)
    }

    override fun updateNameTextView() {
        view.updateNameTextView(element.name)
    }

    override fun updateDescriptionTextView() {
        view.updateDescriptionTextView(element.about)
    }

    override fun updateCountryTextView() {
        view.updateCountryTextView(element.country)
    }

    override fun updateTextSelectedButton() {
        view.updateTextSelectedButton(element.getTextSelected())
    }

    override fun updateColorSelectedButton() {
        view.updateColorSelectedButton(element.getColorSelected())
    }

    override fun onAttach(view: ElementContract.View) {
        this.view = view
        element = (view as CityActivity).intent.getParcelableExtra(CityActivity.CITY_KEY) as City
        init()
    }

    override fun setOnClickListenerSelectedButton() {
        element.select = !element.select
        updateTextSelectedButton()
        updateColorSelectedButton()
    }

    override fun init() {
        loadPhoto()
        updateTextSelectedButton()
        updateColorSelectedButton()
        updateSiteTextView()
        updateNameTextView()
        updateDescriptionTextView()
        updateCountryTextView()
        view.setOnClickListenerSelectedButton()
    }

    override fun onFinish() {
        val intent = Intent(view as CityActivity, MainActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, element)
        (view as CityActivity).setResult(AppCompatActivity.RESULT_OK, intent)
    }
}