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
        presenter.onAttach(this, intent.getLongExtra(CityActivity.CITY_KEY, 0), CityActivity.CITY_KEY)
        selectedButton.setOnClickListener { presenter.setOnClickListenerSelectedButton(intent.getLongExtra(CityActivity.CITY_KEY, 0), CityActivity.CITY_KEY) }
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
        cityNameTextView.addTextChangedListener(presenter.getTextValidator(cityNameTextView))
        cityNameTextView.filters = presenter.getInputFilters()
    }


    override fun updateDescriptionText(description: String?) {
        cityDescriptionTextView.setText(description)
    }

    override fun updateCountryText(country: String?) {
        cityСountryTextView.setText(country)
    }

    override fun loadPhoto(url: String?) {
        Picasso.get()
            .load(url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(сityPhotoView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_city_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
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