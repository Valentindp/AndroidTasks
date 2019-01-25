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

    fun update(){

    }

    override fun saveAll(elements: List<RealmObject>) {

    }

    override fun deleteAll(elements: List<RealmObject>) {

    }

    override fun delete(element: RealmObject) {

    }

    fun getCity(id: Long): BaseContract.Model = RealmManager.getRealm().where(City::class.java).equalTo("id", id).findFirst() as BaseContract.Model

    fun getAllCities(): List<City> = RealmManager.getRealm().where(City::class.java).findAll()

    fun getPark(id: Long): BaseContract.Model = RealmManager.getRealm().where(Park::class.java).equalTo("id", id).findFirst() as BaseContract.Model

    fun getAllParks(): List<Park> = RealmManager.getRealm().where(Park::class.java).findAll()

    fun getSomeElement() : BaseContract.Model = RealmManager.getRealm().where(City::class.java).findFirst() as BaseContract.Model

    fun getElement(id: Long, key: String): BaseContract.Model {
        return when (key) {
            "CITY_KEY" -> getCity(id)
            "PARK_KEY" -> getPark(id)

            else -> getSomeElement()
        }
    }

}


