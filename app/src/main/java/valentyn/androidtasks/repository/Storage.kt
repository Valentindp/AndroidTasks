package valentyn.androidtasks.repository

import valentyn.androidtasks.views.BaseContract

interface Storage {

    fun save(element: BaseContract.Model)

}