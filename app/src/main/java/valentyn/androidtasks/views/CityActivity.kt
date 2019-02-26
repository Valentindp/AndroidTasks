package valentyn.androidtasks.views

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.presenters.ElementPresenter
import android.content.Intent
import android.net.Uri

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
        сityPhotoView.setOnClickListener {
            startActivityForResult(presenter.getImageIntent(), REQUEST_TAKE_PHOTO)
        }
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

    override fun updateImageUri(uri: Uri?) {
        сityPhotoView.setImageURI(uri)
        cityUriView.text = uri.toString()
    }

    override fun loadPhoto(url: String?) {

        if (url != null && url.isNotEmpty()) {
            cityUriView.text = url
            Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(сityPhotoView)
        }
    }

    private fun saveElement() {
        presenter.saveElement(
            id = intent.getStringExtra(CityActivity.CITY_KEY),
            name = cityNameTextView.text.toString(),
            url = cityUriView.text.toString(),
            about = cityDescriptionTextView.text.toString(),
            country = cityСountryTextView.text.toString(),
            site = citySiteTextView.text.toString(),
            select = selectedButton.text.toString(),
            key = CityActivity.CITY_KEY
        )
    }


    override fun getContextView(): Context {
        return this
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK) {

        }
    }

    override fun onFinish() {
        finish()
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
        const val REQUEST_TAKE_PHOTO: Int = 1

    }

}