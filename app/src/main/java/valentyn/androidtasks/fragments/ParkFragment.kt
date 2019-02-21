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
import valentyn.androidtasks.R
import valentyn.androidtasks.adapters.ParksAdapter
import valentyn.androidtasks.repository.RealmRepository
import valentyn.androidtasks.views.ParkActivity

class ParkFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_park, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        park_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter =
                ParksAdapter(RealmRepository.getRealmObjectList(ParkActivity.PARK_KEY)) { id: String? -> onParkClicked(id) }
        }
    }

    private fun onParkClicked(id: String?) {
        val intent = Intent(activity, ParkActivity::class.java)
        intent.putExtra(ParkActivity.PARK_KEY, id)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            park_recyclerView.adapter =
                ParksAdapter(RealmRepository.getRealmObjectList(ParkActivity.PARK_KEY)) { id: String? -> onParkClicked(id) }
        }
    }
}

