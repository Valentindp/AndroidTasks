package valentyn.androidtasks.data.source.repository

import io.realm.Realm
import io.realm.kotlin.where
import valentyn.androidtasks.data.City
import valentyn.androidtasks.data.Park
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.data.source.local.LocalDataSource
import valentyn.androidtasks.data.source.local.RealmDatabase
import valentyn.androidtasks.datadetails.parks.ParkActivity

object DatasRepository : DataSource {

    override fun getData(key: String, dataId: String?, callback: DataSource.GetDataCallback) {

        LocalDataSource.getData(key, dataId, object : DataSource.GetDataCallback {

            override fun onDataLoaded(data: BaseContract.Data) { callback.onDataLoaded(data) }

            override fun onDataNotAvailable() { callback.onDataNotAvailable() }
        })
    }

    override fun getDatas(key: String, callback: DataSource.LoadDatasCallback) {
        LocalDataSource.getDatas(key, object : DataSource.LoadDatasCallback {

            override fun onDatasLoaded(datas: List<BaseContract.Data>) { callback.onDatasLoaded(datas) }

            override fun onDataNotAvailable() { callback.onDataNotAvailable() }
        })
    }

    override fun save(data: BaseContract.Data) {
        LocalDataSource.save(data)
    }

    fun getRealmObject(id: String?, key: String): BaseContract.Data? {
        var realmObject: BaseContract.Data? = null
        val realm = Realm.getDefaultInstance()
        realm.use {
            when (key) {
                CityActivity.CITY_KEY -> realmObject =
                    realm.where<City>().equalTo("id", id).findFirst()
                ParkActivity.PARK_KEY -> realmObject =
                    realm.where<Park>().equalTo("id", id).findFirst()
            }
            if (realmObject != null) realmObject = realm.copyFromRealm(realmObject)
        }
        return realmObject
    }


    fun updateSelect(id: String?, key: String): BaseContract.Data? {
        val realmObject = getRealmObject(id, key)
        if (realmObject != null) {
            realmObject.select = !realmObject.select
            save(realmObject)
        }
        return realmObject
    }

    override fun initDatas() {
        LocalDataSource.initDatas()
    }
}




