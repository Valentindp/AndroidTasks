package valentyn.androidtasks.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_city.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.CityActivity

class CityFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
        updateRecyclerViewAdapter()
    }

    private fun onCityClicked(id: String?) {
        val intent = Intent(activity, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, id)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            updateRecyclerViewAdapter()
        }
    }

    fun updateRecyclerViewAdapter(){
        city_recyclerView.adapter =
            CitysAdapter(RealmRepository.getRealmObjectList(CityActivity.CITY_KEY)) { id: String? -> onCityClicked(id) }
    }

}

