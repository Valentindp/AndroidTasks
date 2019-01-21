package valentyn.androidtasks.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.models.City
import valentyn.androidtasks.presenters.CityActivityPresenter

class CityActivity : AppCompatActivity(), ElementView {


    private var city: City? = null
    private var presenter: CityActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        val city = intent.getParcelableExtra(CITY_KEY) as City
        presenter = CityActivityPresenter(this, intent.getParcelableExtra(CITY_KEY) as City)

        this.city = city
        setModelValues(city)
    }

    private fun setModelValues(city: City) {

        citySiteTextView.text = city.site
        cityNameTextView.text = city.name
        cityDescriptionTextView.text = city.about
        cityСountryTextView.text = city.country

        selectedButton.apply {
            setText(city.getTextSelected())
            setTextColor(city.getColorSelected())

            setOnClickListener {
                city.select = !city.select
                selectedButton.apply {
                    setText(city.getTextSelected())
                    setTextColor(city.getColorSelected())
                }
            }
        }

    }

    override fun setOnClickListenerSelectedButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setElementView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTextSelectedButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setColorSelectedButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        setResult(RESULT_OK, intent)
        super.finish()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}