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
        realm?.beginTransaction()
        try {
            realm?.copyToRealmOrUpdate(element)
            realm?.commitTransaction()
        } catch (e: Exception) {
            realm?.cancelTransaction()
        }
    }

    fun getRealmObject(id: Long?, key: String): BaseContract.Model? {
        return when (key) {
            CityActivity.CITY_KEY -> {
                val realmObject = RealmManager.getRealm()?.where<City>()?.equalTo("id", id)?.findFirst()
                //RealmManager.closeConnection()
                realmObject
            }
            ParkActivity.PARK_KEY -> {
                val realmObject = RealmManager.getRealm()?.where<Park>()?.equalTo("id", id)?.findFirst()
               //RealmManager.closeConnection()
                realmObject
            }
            else -> null
        }
    }

    fun getRealmObjectList(key: String): List<BaseContract.Model> {
        when (key) {
            CityActivity.CITY_KEY -> {
                val realmObjectList = RealmManager.getRealm()?.where<City>()?.findAll()
                if (realmObjectList != null) {
                    //RealmManager.closeConnection()
                    return realmObjectList
                }
            }
            ParkActivity.PARK_KEY -> {
                val realmObjectList = RealmManager.getRealm()?.where<Park>()?.findAll()
                if (realmObjectList != null) {
                    //RealmManager.closeConnection()
                    return realmObjectList
                }
            }
        }
        return emptyList<BaseContract.Model>()
    }

    fun updateSelect(id: Long?, key: String) {
        val realm = RealmManager.getRealm()
        val element = getRealmObject(id, key)

        if (element != null) {
            realm?.beginTransaction()
            element.select = !element.select
            realm?.commitTransaction()
        }
    }

    override fun saveAll(elements: List<RealmObject>) {}

    override fun deleteAll(elements: List<RealmObject>) {}

    override fun delete(element: RealmObject) {}

    fun initDB() {
        if (getRealmObjectList(CityActivity.CITY_KEY).isEmpty()) for (item in CityRepository.dataCitys) save(item)
        if (getRealmObjectList(ParkActivity.PARK_KEY).isEmpty()) for (item in ParkRepository.dataParks) save(item)
    }
}


