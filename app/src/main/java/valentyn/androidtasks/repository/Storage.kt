package valentyn.androidtasks.repository

interface Storage<ModelType> {

    interface Transaction<T> {
        fun transact(): T
    }

    fun save(element: ModelType)
    fun saveAll(elements: List<ModelType>)

    fun deleteAll(elements: List<ModelType>)
    fun delete(element: ModelType)

}