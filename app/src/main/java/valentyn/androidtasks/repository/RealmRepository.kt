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
        thread {
            val realm = Realm.getDefaultInstance()
            realm.use {
                realm.executeTransaction { realm.copyToRealmOrUpdate(element) }
            }
        }
    }

    fun getRealmObject(id: Long?, key: String): BaseContract.Model? {
        var realmObject: RealmObject? = null

        val realm = Realm.getDefaultInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> realmObject =
                    realm.where<City>().equalTo("id", id).findFirst()
                ParkActivity.PARK_KEY -> realmObject =
                    realm.where<Park>().equalTo("id", id).findFirst()
            }
            if (realmObject != null) realmObject = realm.copyFromRealm(realmObject) else realmObject
        }

        return realmObject as BaseContract.Model
    }

    fun getRealmObjectList(key: String): List<BaseContract.Model> {
        var realmList: List<BaseContract.Model> = emptyList()
        val realm = Realm.getDefaultInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> realmList = realm.copyFromRealm(realm.where<City>().findAll())
                ParkActivity.PARK_KEY -> realmList = realm.copyFromRealm(realm.where<Park>().findAll())
            }
        }
        return realmList
    }

    fun updateSelect(id: Long?, key: String): BaseContract.Model? {
        val realm = Realm.getDefaultInstance()
        var realmObject: BaseContract.Model? = null
        realm.use {
            realmObject = getRealmObject(id, key)
            if (realmObject != null) {
                realmObject!!.select = !realmObject!!.select
                realm.executeTransaction { realm.insertOrUpdate(realmObject as RealmObject) }
            }
        }
        return realmObject
    }

    fun updateSelect(realmObject: BaseContract.Model?): BaseContract.Model? {
        val realm = Realm.getDefaultInstance()
        realm.use {
            if (realmObject != null) {
                realmObject.select = !realmObject.select
                realm.executeTransaction { realm.insertOrUpdate(realmObject as RealmObject) }
            }
            return realmObject
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




