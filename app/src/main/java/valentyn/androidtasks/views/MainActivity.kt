package valentyn.androidtasks.views

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import valentyn.androidtasks.views.DatasAdapter.DataViewHolder
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.androidtasks.R
import valentyn.androidtasks.presenters.DatasRepository

class MainActivity : AppCompatActivity(), View.OnClickListener {



    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.setTitle("Titulo toolbar")

        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.app_name)

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)

        viewManager = LinearLayoutManager(this)
        viewAdapter = DatasAdapter(DatasRepository().getDataset())

        recyclerView = findViewById<RecyclerView>(R.id.datas_recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
    }
  }

    fun newInstance(context: Context): Intent {return Intent(this, CityActivity::class.java) }

    override fun onClick(v: View) {
        val holder : DataViewHolder = recyclerView.findContainingViewHolder(v) as DataViewHolder
        val intent = newInstance(this)
        intent.putExtra(CityActivity.CITY_KEY, holder.getCity())
        startActivities(arrayOf(intent))
    }
}
