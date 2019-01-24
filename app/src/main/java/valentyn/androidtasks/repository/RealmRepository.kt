package valentyn.androidtasks.repository

import android.content.Context
import io.realm.Realm
import valentyn.androidtasks.views.BaseContract

class RealmRepository : Storage<BaseContract.Model> {

    private var realm: Realm? = null

    override fun save(element: BaseContract.Model) {

    }

    override fun saveAll(elements: List<BaseContract.Model>) {

    }

    override fun deleteAll(element: List<BaseContract.Model>) {

    }

    override fun delete(element: BaseContract.Model) {

    }

    fun getRealm(): Realm? {

        if (realm != null) return realm

        realm = Realm.getInstance(Realm.getDefaultConfiguration())

        return realm
    }

    fun init(context: Context) {
        Realm.init(context)
    }
}