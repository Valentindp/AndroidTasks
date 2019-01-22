package valentyn.androidtasks.presenters

import android.content.Intent
import valentyn.androidtasks.adapters.ParksAdapter
import valentyn.androidtasks.fragments.ParkFragment
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.repository.ParkRepository
import valentyn.androidtasks.views.ParkActivity

class ParkFragmentPresenter(private val fragment: ParkFragment) {

    private val dataset: List<Park> = ParkRepository.dataParks

    private fun onParkClicked(park: Park) {
        val intent = Intent(fragment.activity, ParkActivity::class.java)
        intent.putExtra(ParkActivity.PARK_KEY, park)
        fragment.startActivityForResult(intent, 1)
    }

    fun getRecyclerViewAdapter(): ParksAdapter = ParksAdapter(dataset) { park: Park -> onParkClicked(park) }

    fun updateSelect(park: Park): Unit {
        dataset.filter { it.id == park.id }.map { it.select = park.select }
    }

}