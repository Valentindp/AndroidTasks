package valentyn.androidtasks.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.R
import valentyn.androidtasks.presenters.ElementPresenter

class CityActivity : AppCompatActivity(), ElementContract.View {

    private var presenter: ElementPresenter = ElementPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
        presenter.onAttach(this, intent.getStringExtra(CityActivity.CITY_KEY), CityActivity.CITY_KEY)
        selectedButton.setOnClickListener {
            presenter.setOnClickListenerSelectedButton(
                intent.getStringExtra(
                    CityActivity.CITY_KEY
                ), CityActivity.CITY_KEY
            )
        }

        cityNameTextView.addTextChangedListener(presenter.getTextValidator(cityNameTextView))
        citySiteTextView.addTextChangedListener(presenter.getTextValidator(citySiteTextView))
        cityDescriptionTextView.addTextChangedListener(presenter.getTextValidator(cityDescriptionTextView))
        cityСountryTextView.addTextChangedListener(presenter.getTextValidator(cityСountryTextView))
    }

    override fun updateTextSelectedButton(value: Int) {
        selectedButton.setText(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        selectedButton.setTextColor(value)
    }

    override fun updateSiteText(site: String?) {
        citySiteTextView.setText(site)
    }

    override fun updateNameText(name: String?) {
        cityNameTextView.setText(name)
    }


    override fun updateDescriptionText(description: String?) {
        cityDescriptionTextView.setText(description)
    }

    override fun updateCountryText(country: String?) {
        cityСountryTextView.setText(country)
    }

    override fun loadPhoto(url: String?) {

        if (url != null && url.isNotEmpty())
            Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(сityPhotoView)
    }

    private fun saveElement() {
        presenter.saveElement(
            id = intent.getStringExtra(CityActivity.CITY_KEY),
            name = cityNameTextView.text.toString(),
            url = "",
            about = cityDescriptionTextView.text.toString(),
            country = cityСountryTextView.text.toString(),
            site = citySiteTextView.text.toString(),
            key = CityActivity.CITY_KEY
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_city_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            true
        }
        R.id.action_save -> {
            saveElement()
            onBackPressed()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun finish() {
        if (presenter.isChangeElement) setResult(AppCompatActivity.RESULT_OK)
        super.finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}