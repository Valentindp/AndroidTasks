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

class ParkActivity : AppCompatActivity() {

    var park: Park? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)

        setSupportActionBar(toolbar_park)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val park = intent.getSerializableExtra(PARK_KEY) as Park

        this.park = park

        parkSiteTextView.text = park.site
        parkNameTextView.text = park.name
        parkDescriptionTextView.text = park.about
        parkСountryTextView.text = park.country

        selectedButton.setText(park.getTextSelected())
        selectedButton.setTextColor(park.getColorSelected())

        Picasso.get()
            .load(park.url)
            .fit()
            .error(R.drawable.ic_error_black_24dp)
            .into(сityPhotoView)

        selectedButton.setOnClickListener {
            park.select = !park.select
            selectedButton.setText(park.getTextSelected())
            selectedButton.setTextColor(park.getColorSelected())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_park_menu,menu)
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
        intent.putExtra(ParkActivity.PARK_KEY, park)
        setResult(RESULT_OK, intent)
        super.finish()
    }


    companion object {

        const val PARK_KEY = "PARK_KEY"

    }

}