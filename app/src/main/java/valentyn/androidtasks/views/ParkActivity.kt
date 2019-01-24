package valentyn.androidtasks.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_park.*
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.presenters.ElementPresenter

class ParkActivity : AppCompatActivity(), ElementContract.View {

    private var presenter: ElementPresenter = ElementPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)

        setSupportActionBar(toolbar_park)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        presenter.onAttach(this, intent.getParcelableExtra(ParkActivity.PARK_KEY) as Park)
        selectedButton.setOnClickListener { presenter.setOnClickListenerSelectedButton() }
    }


    override fun updateTextSelectedButton(value: Int) {
        selectedButton.setText(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        selectedButton.setTextColor(value)
    }

    override fun updateSiteText(site: String) {
        parkSiteTextView.text = site
    }

    override fun updateNameText(name: String) {
        parkNameTextView.text = name
    }

    override fun updateDescriptionText(description: String) {
        parkDescriptionTextView.text = description
    }

    override fun updateCountryText(country: String) {
        parkСountryTextView.text = country
    }

    override fun loadPhoto(url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(parkPhotoView)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_park_menu, menu)
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
        intent.putExtra(ParkActivity.PARK_KEY, presenter.element)
        setResult(AppCompatActivity.RESULT_OK, intent)
        super.finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {

        const val PARK_KEY = "PARK_KEY"

    }
}