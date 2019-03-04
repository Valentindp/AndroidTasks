package valentyn.androidtasks.mainview.mainfragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_park.*
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.R
import valentyn.androidtasks.mainview.adapters.ParkListAdapter
import valentyn.androidtasks.datadetails.parks.ParkActivity
import valentyn.androidtasks.mainview.DataContract
import valentyn.androidtasks.mainview.DatasPresenter
import valentyn.androidtasks.mainview.adapters.DataItemListener

class ParkListFragment : Fragment(), DataContract.View {

    val presenter: DataContract.Presenter = DatasPresenter()
    private val dataItemListener: DataItemListener = object : DataItemListener {
        override fun onTaskClick(clickedData: BaseContract.Data) {
            presenter.openDataDetails(clickedData)
        }
    }
    private val adapterPark: ParkListAdapter = ParkListAdapter(ArrayList(), dataItemListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_park, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this, ParkActivity.PARK_KEY)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        park_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterPark
        }
    }

    override fun showAllData(dataList: List<BaseContract.Data>) { adapterPark.updateData(dataList) }

    override fun showAddData() {}

    override fun showDataDetailsUi(dataId: String) {
        val intent = Intent(activity, ParkActivity::class.java)
        intent.putExtra(ParkActivity.PARK_KEY, id)
        startActivityForResult(intent, 1)
    }

    override fun showSuccessfullySavedMessage() { showMessage(getString(R.string.data_saved)) }

    override fun showLoadingDataListError() { showMessage(getString(R.string.loading_datas_error)) }

    override fun showSuccessfullyUpdatedMessage() { showMessage(getString(R.string.data_updated)) }

    private fun showMessage(message: String) { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) { presenter.result(requestCode, resultCode) }

    override fun onStop() {
        presenter.onDetach()
        super.onStop()
    }
}

