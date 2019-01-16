package valentyn.androidtasks.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_park.*
import kotlinx.android.synthetic.main.fragment_park.view.*
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.ParksAdapter
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.repository.ParkRepository
import valentyn.androidtasks.views.ParkActivity

class ParkFragment : Fragment() {

    private val dataset: List<Park> = ParkRepository.dataParks

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_park, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        park_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = ParksAdapter(dataset) { park: Park -> cityClicked(park) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            val park = intent?.getParcelableExtra(ParkActivity.PARK_KEY) as Park
            dataset.filter { it.id == park.id }.map { it.select = park.select }
            park_recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun cityClicked(park: Park) {
        val intent = Intent(activity, ParkActivity::class.java)
        intent.putExtra(ParkActivity.PARK_KEY, park)
        startActivityForResult(intent, 1)
    }
}

