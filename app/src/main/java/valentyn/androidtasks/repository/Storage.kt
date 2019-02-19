package valentyn.androidtasks.repository

import valentyn.androidtasks.views.BaseContract

interface Storage<Model> {

    fun save(element: BaseContract.Model)

}