package valentyn.androidtasks.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.FragmentsPagerAdapter
import valentyn.androidtasks.repository.RealmRepository


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
        RealmRepository.initDB()

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

        R.id.action_favorite -> {
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}













