package valentyn.androidtasks.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.models.City
import valentyn.androidtasks.presenters.CityRepository


class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewAdapter: CitysAdapter
    private lateinit var mViewManager: LinearLayoutManager
    private lateinit var mDataset:List<City>
    private var mPosition:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        mViewManager = LinearLayoutManager(this)
        mDataset = CityRepository().getDataset()
        mViewAdapter = CitysAdapter(mDataset, { city: City, position: Int ->  CityClicked(city, position)})

        mRecyclerView = datas_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mViewManager
            adapter = mViewAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main,menu)
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

    private fun CityClicked(city: City, position: Int) {
        mPosition = position
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        startActivityForResult(intent, 1)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        if (requestCode == 1) {
            if (resultCode == 1) {
                mDataset.get(mPosition).Select =  !mDataset.get(mPosition).Select
                mViewAdapter.notifyDataSetChanged()
            }
        }
    }

}






