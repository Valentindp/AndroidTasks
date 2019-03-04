package valentyn.androidtasks.mainview.mainfragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_city.*
import valentyn.androidtasks.BaseContract
import valentyn.androidtasks.R
import valentyn.androidtasks.mainview.adapters.CityListAdapter
import valentyn.androidtasks.datadetails.cities.CityActivity
import valentyn.androidtasks.mainview.DataContract
import valentyn.androidtasks.mainview.DatasPresenter
import valentyn.androidtasks.mainview.adapters.DataItemListener

class CityListFragment : Fragment(), DataContract.View {

    val presenter: DataContract.Presenter = DatasPresenter()
    private val dataItemListener: DataItemListener = object : DataItemListener {
        override fun onTaskClick(clickedData: BaseContract.Data) {
            presenter.openDataDetails(clickedData)
        }
    }
    private val adapterCity: CityListAdapter = CityListAdapter(ArrayList(), dataItemListener)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this, CityActivity.CITY_KEY)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterCity
        }
    }

    override fun showAllData(dataList: List<BaseContract.Data>) { adapterCity.updateData(dataList) }

    override fun showAddData() {}

    override fun showDataDetailsUi(dataId: String) {
        val intent = Intent(context, CityActivity::class.java)
        intent.putExtra(CityActivity.CITY_KEY, dataId)
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

