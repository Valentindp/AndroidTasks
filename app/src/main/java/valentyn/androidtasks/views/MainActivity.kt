package valentyn.androidtasks.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.models.City
import valentyn.androidtasks.repository.CityRepository


class MainActivity : AppCompatActivity() {

    private lateinit var mViewManager: LinearLayoutManager
    private val mDataset: List<City> = CityRepository.mDataset

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        mViewManager = LinearLayoutManager(this)

        city_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mViewManager
            adapter = CitysAdapter(mDataset, { city: City -> cityClicked(city) })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            true
        }

        R.id.action_favorite -> {
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun cityClicked(city: City) {
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        startActivityForResult(intent, 1)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        if (resultCode == RESULT_OK) {
            val city = intent?.getSerializableExtra(CityActivity.CITY_KEY) as City
            mDataset.filter { it.id == city.id }.map { it.select = city.select }
            city_recyclerView.adapter?.notifyDataSetChanged()
        }
    }

}






