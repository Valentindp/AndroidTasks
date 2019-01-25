package valentyn.androidtasks.repository

import io.realm.Realm

object RealmManager {

    private val realmList = ThreadLocal<Realm>()

    fun getRealm(): Realm {
        var realm = realmList.get()
        if (realm == null) {
            realm = Realm.getDefaultInstance()
            realmList.set(realm)
        }
        return realm
    }

    fun closeConnection() {
        val realm = realmList.get()
        if (realm != null) {
            realm.close()
            realmList.remove()
        }
    }
}