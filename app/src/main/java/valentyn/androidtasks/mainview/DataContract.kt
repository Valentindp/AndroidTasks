package valentyn.androidtasks.mainview

import valentyn.androidtasks.BaseContract

interface DataContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View, key: String = "")

        fun addNewData()

        fun loadAllData()

        fun openDataDetails(requestedData: BaseContract.Data)

        fun result(requestCode: Int, resultCode: Int)
    }

    interface View : BaseContract.View {

        fun showAllData(dataList: List<BaseContract.Data>)

        fun showAddData()

        fun showDataDetailsUi(dataId: String)

        fun showSuccessfullySavedMessage()

        fun showLoadingDataListError()

        fun showSuccessfullyUpdatedMessage()
    }

}