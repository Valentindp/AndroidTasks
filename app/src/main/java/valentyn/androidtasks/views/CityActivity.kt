package valentyn.androidtasks.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
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
        presenter.onAttach(this, intent.getLongExtra(CityActivity.CITY_KEY,0), CityActivity.CITY_KEY)
        selectedButton.setOnClickListener { presenter.setOnClickListenerSelectedButton() }
    }

    override fun updateTextSelectedButton(value: Int) {
        selectedButton.setText(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        selectedButton.setTextColor(value)
    }

    override fun updateSiteText(site: String?) {
        citySiteTextView.text = site
    }

    override fun updateNameText(name: String?) {
        cityNameTextView.text = name
    }

    override fun updateDescriptionText(description: String?) {
        cityDescriptionTextView.text = description
    }

    override fun updateCountryText(country: String?) {
        cityСountryTextView.text = country
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
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, presenter.element?.id)
        setResult(AppCompatActivity.RESULT_OK, intent)
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