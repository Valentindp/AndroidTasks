package valentyn.androidtasks.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.presenters.CityActivityPresenter

class CityActivity : AppCompatActivity(), ElementContract.View {

    private var presenter: CityActivityPresenter = CityActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
        presenter.onAttach(this)
    }

    override fun setOnClickListenerSelectedButton() {
        selectedButton.setOnClickListener { presenter.setOnClickListenerSelectedButton() }
    }

    override fun updateTextSelectedButton(value: Int) {
        selectedButton.setText(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        selectedButton.setTextColor(value)
    }

    override fun updateSiteTextView(site: String) {
        citySiteTextView.text = site
    }

    override fun updateNameTextView(name: String) {
        cityNameTextView.text = name
    }

    override fun updateDescriptionTextView(description: String) {
        cityDescriptionTextView.text = description
    }

    override fun updateCountryTextView(country: String) {
        cityСountryTextView.text = country
    }

    override fun loadPhoto(url: String) {
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
        presenter.onFinish()
        super.finish()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}