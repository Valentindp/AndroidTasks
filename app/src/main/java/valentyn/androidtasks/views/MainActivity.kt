package valentyn.androidtasks.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.FragmentsPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewPager.adapter = FragmentsPagerAdapter(supportFragmentManager)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_city -> viewPager.currentItem = 0
                R.id.tab_park -> viewPager.currentItem = 1
            }
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
        R.id.action_add_element -> {
            startNewElementActivity()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun startNewElementActivity() {
        when (viewPager.currentItem) {
            0 -> {
                val intent = Intent(this, CityActivity::class.java)
                intent.putExtra(CityActivity.CITY_KEY, -1)
                startActivityForResult(intent, 1)
            }
            1 -> {
                val intent = Intent(this, ParkActivity::class.java)
                intent.putExtra(ParkActivity.PARK_KEY, -1)
                startActivityForResult(intent, 1)
            }
        }

    }

}













