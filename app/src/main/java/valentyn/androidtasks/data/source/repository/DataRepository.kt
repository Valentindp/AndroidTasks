package valentyn.androidtasks.data.source.repository


import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.data.source.local.LocalDataSource
import java.util.LinkedHashMap

object DataRepository : DataSource {

    var cachedDataMap: MutableMap<String, BaseContract.Data>? = null
    var cacheIsDirty = false

    override fun getData(key: String, dataId: String?, callback: DataSource.GetDataCallback) {

        val cachedData = getDataWithId(dataId)

        if (cachedData != null) {
            callback.onDataLoaded(cachedData)
            return
        }

        LocalDataSource.getData(key, dataId, object : DataSource.GetDataCallback {

            override fun onDataLoaded(data: BaseContract.Data) {

                if (cachedDataMap == null) {
                    cachedDataMap = LinkedHashMap()
                }
                cachedDataMap!![data.id] = data
                callback.onDataLoaded(data)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getAllData(key: String, callback: DataSource.LoadAllDataCallback) {
        LocalDataSource.getAllData(key, object : DataSource.LoadAllDataCallback {

            override fun onAllDataLoaded(dataList: List<BaseContract.Data>) {
                refreshCache(dataList)
                callback.onAllDataLoaded(ArrayList(cachedDataMap!!.values))
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun save(data: BaseContract.Data) {
        LocalDataSource.save(data)

        if (cachedDataMap == null) {
            cachedDataMap = LinkedHashMap()
        }
        cachedDataMap!![data.id] = data
    }

    private fun refreshCache(dataList: List<BaseContract.Data>) {

        if (cachedDataMap == null) {
            cachedDataMap = LinkedHashMap()
        }

        cachedDataMap?.clear()
        dataList.forEach { cachedDataMap!![it.id] = it }
        cacheIsDirty = false
    }

    private fun getDataWithId(dataId: String?): BaseContract.Data? {
        return if (cachedDataMap == null || cachedDataMap!!.isEmpty()) {
            null
        } else {
            cachedDataMap!![dataId]
        }
    }

    fun updateSelect(data: BaseContract.Data?) {
        if (data != null) {
            data.select = !data.select
            LocalDataSource.save(data)
        }
    }

    fun updateSelect(dataId: String?){ updateSelect(getDataWithId(dataId))}

    override fun initAllData() {
        LocalDataSource.initAllData()
    }
}




