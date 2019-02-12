package valentyn.androidtasks.repository

import io.realm.RealmObject
import io.realm.kotlin.where
import valentyn.androidtasks.models.City
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.CityActivity
import valentyn.androidtasks.views.ParkActivity

object RealmRepository : Storage<RealmObject> {

    override fun save(element: RealmObject) {
        val realm = RealmManager.getRealm()
        realm?.executeTransaction { realm.copyToRealmOrUpdate(element) }
    }

    fun getRealmObject(id: Long?, key: String): BaseContract.Model? = when (key) {
        CityActivity.CITY_KEY -> RealmManager.getRealm()?.where<City>()?.equalTo("id", id)?.findFirst()
        ParkActivity.PARK_KEY -> RealmManager.getRealm()?.where<Park>()?.equalTo("id", id)?.findFirst()
        else -> null
    }

    fun getRealmObjectList(key: String): List<BaseContract.Model> = when (key) {
        CityActivity.CITY_KEY -> RealmManager.getRealm()?.where<City>()?.findAll() as List<BaseContract.Model>
        ParkActivity.PARK_KEY -> RealmManager.getRealm()?.where<Park>()?.findAll() as List<BaseContract.Model>
        else -> emptyList()
    }

    fun updateSelect(id: Long?, key: String) {
        val element = getRealmObject(id, key)
        if (element != null) RealmManager.getRealm()?.executeTransaction { element.select = !element.select }
    }

    fun updateSelect(element: BaseContract.Model?) {
        if (element!=null) RealmManager.getRealm()?.executeTransaction { element.select = !element.select }
    }

    override fun saveAll(elements: List<RealmObject>) {}

    override fun deleteAll(elements: List<RealmObject>) {}

    override fun delete(element: RealmObject) {}

    fun initDB() {
        if (getRealmObjectList(CityActivity.CITY_KEY).isEmpty()) for (item in CityRepository.dataCitys) save(item)
        if (getRealmObjectList(ParkActivity.PARK_KEY).isEmpty()) for (item in ParkRepository.dataParks) save(item)
    }
}




