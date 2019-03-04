package valentyn.androidtasks.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import valentyn.androidtasks.BaseContract
import java.util.*

open class Park(
    @PrimaryKey override var id: String = UUID.randomUUID().toString(),
    override var name: String = "",
    override var url: String = "",
    override var description: String = "",
    override var country: String = "",
    override var site: String = "",
    override var select: Boolean = false
) : BaseContract.Data, RealmObject() {}





