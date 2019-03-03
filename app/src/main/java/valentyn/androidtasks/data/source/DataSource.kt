package valentyn.androidtasks.data.source

import valentyn.androidtasks.BaseContract

interface DataSource {

    interface LoadDatasCallback {

        fun onDatasLoaded(datas: List<BaseContract.Data>)

        fun onDataNotAvailable()
    }

    interface GetDataCallback {

        fun onDataLoaded(data: BaseContract.Data)

        fun onDataNotAvailable()
    }

    fun getData(key: String = "", dataId: String?, callback: GetDataCallback)

    fun getDatas(key: String = "", callback: LoadDatasCallback)

    fun save(data: BaseContract.Data)

    fun initDatas()

}