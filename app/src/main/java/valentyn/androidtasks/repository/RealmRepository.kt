package valentyn.androidtasks.repository

import io.realm.RealmObject
import valentyn.androidtasks.models.City
import valentyn.androidtasks.models.Park
import valentyn.androidtasks.views.BaseContract

object RealmRepository : Storage<RealmObject> {


    override fun save(element: RealmObject) {
        val realm = RealmManager.getRealm()
        realm.beginTransaction()
        try {
            realm.copyToRealmOrUpdate(element)
            realm.commitTransaction()
        } catch (e: Exception) {
            realm.cancelTransaction()
        }
    }

    fun update() {

    }

    override fun saveAll(elements: List<RealmObject>) {

    }

    override fun deleteAll(elements: List<RealmObject>) {

    }

    override fun delete(element: RealmObject) {

    }

    fun getCity(id: Long?): BaseContract.Model? = RealmManager.getRealm().where(BaseContract.Model::class.java).equalTo("id", id).findFirst()

    fun getAllCities(): List<BaseContract.Model?> = RealmManager.getRealm().where(BaseContract.Model::class.java).findAll()

    fun getPark(id: Long?): BaseContract.Model? = RealmManager.getRealm().where(BaseContract.Model::class.java).equalTo("id", id).findFirst()

    fun getAllParks(): List<BaseContract.Model?> = RealmManager.getRealm().where(BaseContract.Model::class.java).findAll()

    fun getSomeElement(): BaseContract.Model? =
        RealmManager.getRealm().where(City::class.java).findFirst()

    fun getElement(id: Long?, key: String): BaseContract.Model? {
        return when (key) {
            "CITY_KEY" -> getCity(id)
            "PARK_KEY" -> getPark(id)

            else -> getSomeElement()
        }
    }

}


