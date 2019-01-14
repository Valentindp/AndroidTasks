package valentyn.androidtasks.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.models.City

class CityActivity : AppCompatActivity() {

    var mCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val city = intent.getSerializableExtra(CITY_KEY) as City

        mCity = city

        citySiteTextView.text = city.site
        cityNameTextView.text = city.name
        cityDescriptionTextView.text = city.about
        cityСountryTextView.text = city.country

        selectedButton.setText(city.getTextSelected())
        selectedButton.setTextColor(city.getColorSelected())

        Picasso.get()
            .load(city.url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(сityPhotoView)

        selectedButton.setOnClickListener {
            city.select = !city.select
            selectedButton.setText(city.getTextSelected())
            selectedButton.setTextColor(city.getColorSelected())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_city_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            true
        }
        android.R.id.home ->{
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }


    override fun finish() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, mCity)
        setResult(RESULT_OK, intent)
        super.finish()
    }


    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}