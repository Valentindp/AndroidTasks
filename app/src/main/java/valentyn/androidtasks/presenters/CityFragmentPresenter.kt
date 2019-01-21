package valentyn.androidtasks.presenters

import android.content.Intent
import valentyn.androidtasks.adapters.CitysAdapter
import valentyn.androidtasks.fragments.CityFragment
import valentyn.androidtasks.models.City
import valentyn.androidtasks.repository.CityRepository
import valentyn.androidtasks.views.CityActivity

class CityFragmentPresenter(private val fragment: CityFragment) {

    private val dataset: List<City> = CityRepository.dataCitys

    private fun onCityClicked(city: City) {
        val intent = Intent(fragment.activity, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, city)
        fragment.startActivityForResult(intent, 1)
    }

    fun getRecyclerViewAdapter(): CitysAdapter = CitysAdapter(dataset) { city: City -> onCityClicked(city) }

    fun updateElement(city: City): Unit {
        dataset.filter { it.id == city.id }.map { it.select = city.select }
    }
}