package valentyn.androidtasks.mainview

import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.data.source.repository.DataRepository

class DatasPresenter : DataContract.Presenter {

    var view: DataContract.View? = null
    var key: String = ""

    override fun openDataDetails(requestedData: BaseContract.Data) {
        view?.showDataDetailsUi(requestedData.id)
    }

    override fun addNewData() {

    }

    override fun loadAllData() {
        DataRepository.getAllData(key, object : DataSource.LoadAllDataCallback {
            override fun onAllDataLoaded(dataList: List<BaseContract.Data>) {
                view?.showAllData(dataList)
            }

            override fun onDataNotAvailable() {
                view?.showLoadingDataListError()
            }
        })
    }

    override fun result(requestCode: Int, resultCode: Int) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            loadAllData()
            view?.showSuccessfullyUpdatedMessage()
        }
    }

    override fun start() {
        loadAllData()
    }

    override fun onAttach(view: DataContract.View, key: String) {
        this.view = view
        this.key = key
    }

    override fun onDetach() {
        this.view = null
    }

}