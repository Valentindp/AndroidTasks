package valentyn.androidtasks.data.source

import valentyn.androidtasks.BaseContract

interface DataSource {

    interface LoadAllDataCallback {

        fun onAllDataLoaded(dataList: List<BaseContract.Data>)

        fun onDataNotAvailable()
    }

    interface GetDataCallback {

        fun onDataLoaded(data: BaseContract.Data)

        fun onDataNotAvailable()
    }

    fun getData(key: String = "", dataId: String?, callback: GetDataCallback)

    fun getAllData(key: String = "", callback: LoadAllDataCallback)

    fun save(data: BaseContract.Data)

    fun initAllData()

}