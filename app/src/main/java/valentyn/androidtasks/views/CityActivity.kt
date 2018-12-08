package valentyn.androidtasks.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.R
import valentyn.androidtasks.models.City

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val city: City = intent.getSerializableExtra(CITY_KEY) as City

    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}