package valentyn.androidtasks.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.fragment_city.view.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.models.City
import valentyn.androidtasks.repository.CityRepository
import valentyn.androidtasks.views.CityActivity

class CityFragment : Fragment()  {

    private val mDataset: List<City> = CityRepository.DataCitys

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_city, container, false)

        view.city_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = CitysAdapter(mDataset, { city: City -> cityClicked(city) })
        }
        return view //super.onCreateView(inflater, container, savedInstanceState)
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        if (resultCode == AppCompatActivity.RESULT_OK) {
            val city = intent?.getSerializableExtra(CityActivity.CITY_KEY) as City
            mDataset.filter { it.id == city.id }.map { it.select = city.select }
            city_recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun cityClicked(city: City) {
        val intent = Intent(activity, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        startActivityForResult(intent, 1)
    }


}
