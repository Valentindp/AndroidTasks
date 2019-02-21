package valentyn.androidtasks.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_park.*
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

        presenter.onAttach(this, intent.getStringExtra(ParkActivity.PARK_KEY), ParkActivity.PARK_KEY)
        selectedButton.setOnClickListener {
            presenter.setOnClickListenerSelectedButton(
                intent.getStringExtra(
                    ParkActivity.PARK_KEY
                ), ParkActivity.PARK_KEY
            )
        }

        parkNameTextView.addTextChangedListener(presenter.getTextValidator(parkNameTextView))
        parkSiteTextView.addTextChangedListener(presenter.getTextValidator(parkSiteTextView))
        parkDescriptionTextView.addTextChangedListener(presenter.getTextValidator(parkDescriptionTextView))
        parkСountryTextView.addTextChangedListener(presenter.getTextValidator(parkСountryTextView))
    }


    override fun updateTextSelectedButton(value: Int) {
        selectedButton.setText(value)
    }

    override fun updateColorSelectedButton(value: Int) {
        selectedButton.setTextColor(value)
    }

    override fun updateSiteText(site: String?) {
        parkSiteTextView.setText(site)
    }

    override fun updateNameText(name: String?) {
        parkNameTextView.setText(name)
    }

    override fun updateDescriptionText(description: String?) {
        parkDescriptionTextView.setText(description)
    }

    override fun updateCountryText(country: String?) {
        parkСountryTextView.setText(country)
    }

    override fun loadPhoto(url: String?) {

        if (url != null && url.isNotEmpty())
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
        if (presenter.isChangeElement) setResult(AppCompatActivity.RESULT_OK)
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