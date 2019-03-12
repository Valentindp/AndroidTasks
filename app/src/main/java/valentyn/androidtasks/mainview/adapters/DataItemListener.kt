package valentyn.androidtasks.mainview.adapters

import valentyn.androidtasks.BaseContract

interface DataItemListener {
    fun onTaskClick(clickedData: BaseContract.Data)
}