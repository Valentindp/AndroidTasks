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
import valentyn.androidtasks.R
import valentyn.androidtasks.models.City
import valentyn.androidtasks.presenters.CityFragmentPresenter
import valentyn.androidtasks.views.CityActivity


class CityFragment : Fragment() {

    private val presenter: CityFragmentPresenter = CityFragmentPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        city_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = presenter.getAdapter()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        if (resultCode == AppCompatActivity.RESULT_OK) {
            presenter.updateElement(intent?.getParcelableExtra(CityActivity.CITY_KEY) as City)
            city_recyclerView.adapter?.notifyDataSetChanged()
        }
    }

}

