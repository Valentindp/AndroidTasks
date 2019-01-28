package valentyn.androidtasks.repository

interface Storage<Model> {

    fun save(element: Model)
    fun saveAll(elements: List<Model>)


    fun deleteAll(elements: List<Model>)
    fun delete(element: Model)

}