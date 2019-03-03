package valentyn.androidtasks.data.source.local

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.data.City
import valentyn.androidtasks.data.Park
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.datadetails.parks.ParkActivity

object RealmDatabase {

    private fun getInstance(): Realm = Realm.getDefaultInstance()

    fun setRealmConfiguration() {
        setDefaultConfiguration()
    }

    private fun setDefaultConfiguration() {
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
    }

    fun getRealmDatas(key: String): List<BaseContract.Data> {
        var list: List<BaseContract.Data> = emptyList()
        val realm = getInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> list = realm.copyFromRealm(realm.where<City>().findAll())
                ParkActivity.PARK_KEY -> list = realm.copyFromRealm(realm.where<Park>().findAll())
            }
        }
        return list
    }

    fun save(data: BaseContract.Data) {
        val realm = getInstance()
        realm.use {
            realm.executeTransaction { realm.copyToRealmOrUpdate(data) }
        }
    }

    fun getRealmData(key: String, dataId: String?): BaseContract.Data? {
        var data: BaseContract.Data? = null
        val realm = getInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> data =
                    realm.where<City>().equalTo("id", dataId).findFirst()
                ParkActivity.PARK_KEY -> data =
                    realm.where<Park>().equalTo("id", dataId).findFirst()
            }
            if (data != null) data = realm.copyFromRealm(data)
        }
        return data
    }
}