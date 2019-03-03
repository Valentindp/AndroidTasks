package valentyn.androidtasks.data.source.local

import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.data.source.repository.CityRepository
import valentyn.androidtasks.data.source.repository.ParkRepository
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.datadetails.parks.ParkActivity

object LocalDataSource : DataSource {

    override fun initDatas() {
        if (RealmDatabase.getRealmDatas(CityActivity.CITY_KEY).isEmpty()) for (item in CityRepository.dataCitys) save(
            item
        )
        if (RealmDatabase.getRealmDatas(ParkActivity.PARK_KEY).isEmpty()) for (item in ParkRepository.dataParks) save(
            item
        )
    }

    override fun getData(key: String, dataId: String?, callback: DataSource.GetDataCallback) {
        val data: BaseContract.Data? = RealmDatabase.getRealmData(key, dataId)

        if (data != null) callback.onDataLoaded(data) else callback.onDataNotAvailable()
    }

    override fun getDatas(key: String, callback: DataSource.LoadDatasCallback) {
        val list: List<BaseContract.Data> = RealmDatabase.getRealmDatas(key)

        if (list.isEmpty()) { callback.onDataNotAvailable() } else callback.onDatasLoaded(list)
    }

    override fun save(data: BaseContract.Data) { RealmDatabase.save(data) }
}