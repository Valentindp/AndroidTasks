package valentyn.androidtasks.repository

import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where
import valentyn.androidtasks.models.City
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.views.BaseContract
import valentyn.androidtasks.views.CityActivity
import valentyn.androidtasks.views.ParkActivity
import kotlin.concurrent.thread

object RealmRepository : Storage<RealmObject> {

    override fun save(element: RealmObject) {
        Thread(Runnable {
            val realm = Realm.getDefaultInstance()
            realm.use {
                realm.executeTransaction { realm.copyToRealmOrUpdate(element) }
            }
        }).start()
    }

    fun getRealmObject(id: Long?, key: String): BaseContract.Model? {
        var realmObject: BaseContract.Model? = null

        //Thread(Runnable {
        val realm = Realm.getDefaultInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> realmObject = realm.where<City>().equalTo("id", id).findFirst()
                ParkActivity.PARK_KEY -> realmObject = realm.where<Park>().equalTo("id", id).findFirst()
            }
                }
        // }).start()
        return realmObject
    }

    fun getRealmObjectList(key: String): List<BaseContract.Model> {
        var realmList: List<BaseContract.Model> = emptyList()

        //Thread(Runnable {
        val realm = Realm.getDefaultInstance()
        //realm.use {
            when (key) {
                CityActivity.CITY_KEY -> realmList = realm.where<City>().findAll()
                ParkActivity.PARK_KEY -> realmList = realm.where<Park>().findAll()
            }
        //}
        //}).start()
        return realmList
    }

    fun updateSelect(id: Long?, key: String) {
        thread {
            val realm = Realm.getDefaultInstance()
            realm.use {
                val element = getRealmObject(id, key)
                if (element != null) realm.executeTransaction { element.select = !element.select }
            }
        }
    }

    fun updateSelect(element: BaseContract.Model?) {
        thread {
            val realm = Realm.getDefaultInstance()
            realm.use {
                if (element != null) realm.executeTransaction { element.select = !element.select }
            }
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




