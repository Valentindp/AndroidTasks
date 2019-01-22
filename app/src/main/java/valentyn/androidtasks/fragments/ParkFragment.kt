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
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.presenters.ParkFragmentPresenter
import valentyn.androidtasks.views.ParkActivity

class ParkFragment : Fragment() {

    private val presenter: ParkFragmentPresenter = ParkFragmentPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_park, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        park_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = presenter.getRecyclerViewAdapter()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            presenter.updateSelect(intent?.getParcelableExtra(ParkActivity.PARK_KEY) as Park)
            park_recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}

