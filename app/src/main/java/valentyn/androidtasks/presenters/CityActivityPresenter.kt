package valentyn.androidtasks.presenters


import valentyn.androidtasks.models.City
import valentyn.androidtasks.views.ElementView

class CityActivityPresenter(private val cityActivity: ElementView, private val city: City) {

    fun loadPhoto(){
        cityActivity.loadPhoto(city.url)
    }

}