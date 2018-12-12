package valentyn.androidtasks.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.models.City
import valentyn.androidtasks.presenters.CityRepository


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var mDataset:List<City>
    private var mPosition:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.app_name)

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)

        viewManager = LinearLayoutManager(this)
        mDataset = CityRepository().getDataset()
        viewAdapter = CitysAdapter(mDataset, { city: City, position: Int ->  CityClicked(city, position)})

        recyclerView = findViewById<RecyclerView>(R.id.datas_recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun CityClicked(city: City, position: Int) {
        //Toast.makeText(this, "Clicked: ${city.name}", Toast.LENGTH_LONG).show()
        mPosition = position
        val intent = Intent(this, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        startActivityForResult(intent, 1)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        if (requestCode == 1) {
            if (resultCode == 1) {
                mDataset.get(mPosition).Select =  !mDataset.get(mPosition).Select
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

}






