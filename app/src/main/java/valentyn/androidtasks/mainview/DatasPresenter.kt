package valentyn.androidtasks.mainview

import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.data.source.DataSource
import valentyn.androidtasks.data.source.repository.DatasRepository

class DatasPresenter : DatasContract.Presenter {

    var view: DatasContract.View? = null
    var key: String = ""

    override fun openDataDetails(requestedData: BaseContract.Data) {
        view?.showDataDetailsUi(requestedData.id)
    }

    override fun addNewData() {

    }

    override fun loadDatas() {

        DatasRepository.getDatas(key, object : DataSource.LoadDatasCallback {
            override fun onDatasLoaded(datas: List<BaseContract.Data>) {
                view?.showDatas(datas)
            }

            override fun onDataNotAvailable() {
                view?.showLoadingDatasError()
            }
        })
    }

    override fun result(requestCode: Int, resultCode: Int) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            loadDatas()
            view?.showSuccessfullyUpdatedMessage()
        }
    }

    override fun start() {
        loadDatas()
    }

    override fun onAttach(view: DatasContract.View, key: String) {
        this.view = view
        this.key = key
    }

    override fun onDetach() {
        this.view = null
    }

}