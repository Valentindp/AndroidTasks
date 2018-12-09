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

    var mChangeData : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val city = intent.getSerializableExtra(CITY_KEY) as City

        CitySiteTextView.setText(city.Site)
        CityNameTextView.setText(city.name)
        CityDescriptionTextView.setText(city.about)
        CityСountryTextView.setText(city.country)

        SelectedButton.setText(city.getTextSelected())
        SelectedButton.setTextColor(city.getColorSelected())

        Picasso.get()
            .load(city.url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(CityPhotoView)

        SelectedButton.setOnClickListener {
            city.Select = !city.Select
            mChangeData = true
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
            Toast.makeText(this,"Share action",Toast.LENGTH_LONG).show()
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
        if (mChangeData){setResult(1)}
        finish()
        return true
    }


    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}