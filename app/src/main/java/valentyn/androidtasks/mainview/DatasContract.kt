package valentyn.androidtasks.mainview

import valentyn.androidtasks.BaseContract

interface DatasContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View, key: String = "")

        fun addNewData()

        fun loadDatas()

        fun openDataDetails(requestedData: BaseContract.Data)

        fun result(requestCode: Int, resultCode: Int)
    }

    interface View : BaseContract.View {

        fun showDatas(datas: List<BaseContract.Data>)

        fun showAddData()

        fun showDataDetailsUi(dataId: String)

        fun showSuccessfullySavedMessage()

        fun showLoadingDatasError()

        fun showSuccessfullyUpdatedMessage()
    }

}