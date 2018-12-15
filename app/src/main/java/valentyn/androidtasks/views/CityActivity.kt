package valentyn.androidtasks.views

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

    var mSelect : Boolean = false
    var mCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val city = intent.getSerializableExtra(CITY_KEY) as City

        mCity = city
        mSelect = city.Select

        citySiteTextView.setText(city.Site)
        cityNameTextView.setText(city.name)
        cityDescriptionTextView.setText(city.about)
        cityСountryTextView.setText(city.country)

        SelectedButton.setText(city.getTextSelected())
        SelectedButton.setTextColor(city.getColorSelected())

        Picasso.get()
            .load(city.url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(сityPhotoView)

        SelectedButton.setOnClickListener {
            city.Select = !city.Select
            SelectedButton.setText(city.getTextSelected())
            SelectedButton.setTextColor(city.getColorSelected())
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
            onSupportNavigateUp()
        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (mSelect != mCity?.Select){setResult(1)}
        finish()
        return true
    }


    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}