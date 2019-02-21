package valentyn.androidtasks.repository

import valentyn.androidtasks.models.City
import java.util.*

object CityRepository {

    val dataCitys: List<City> = listOf(
        City(
            UUID.randomUUID().toString(),
            "Сан-Франциско",
            "https://s.zagranitsa.com/images/articles/4671/426x270/c9ccd2c338f2b384e12e2452b008a708.jpg",
            "Сан-Франциско – холмистый город на северной оконечности одноименного полуострова на севере Калифорнии, омываемый водами Тихого океана и залива Сан-Франциско",
            "USA",
            "",
            false
        ),
        City(
            UUID.randomUUID().toString(),
            "Сиэтл",
            "https://www.first-americans.spb.ru/wp-content/uploads/2013/04/%D0%A1%D0%B8%D1%8D%D1%82%D0%BB-%D1%88%D1%82%D0%B0%D1%82-%D0%B2%D0%B0%D1%88%D0%B8%D0%BD%D0%B3%D1%82%D0%BE%D0%BD.jpg",
            "Сиэтл – город на берегу залива Пьюджет в регионе Тихоокеанский Северо-Запад, самый крупный в штате Вашингтон",
            "USA",
            "",
            false
        )
        ,
        City(
            UUID.randomUUID().toString(),
            "Атланта",
            "https://s.zagranitsa.com/images/articles/4671/426x270/f6e9b81ceded8fd19bb8764f0cf4750a.jpg",
            "Description",
            "USA",
            "",
            false
        )
        ,
        City(
            UUID.randomUUID().toString(),
            "Филадельфия",
            "https://s.zagranitsa.com/images/articles/4671/426x270/f25ac938093e37caed295d48a257e0b5.jpg",
            "Description",
            "",
            "",
            false
        )
        ,
        City(
            UUID.randomUUID().toString(),
            "Миннеаполис",
            "https://s.zagranitsa.com/images/articles/4671/426x270/d4e1a2666699b0ecd9930cf0a0c4a5c8.jpg",
            "Description",
            "USA",
            "",
            false
        )
        ,
        City(
            UUID.randomUUID().toString(),
            "Нашвилл",
            "https://s.zagranitsa.com/images/articles/4671/426x270/1d79717af24d2e999df058c98ea0e1ee.jpg",
            "Description",
            "USA",
            "",
            false
        )
        ,
        City(
            UUID.randomUUID().toString(),
            "Чикаго",
            "https://s.zagranitsa.com/images/articles/4671/426x270/e241ea77af9731119128776afd548859.jpg",
            "Description",
            "USA",
            "",
            false
        )
    )

}
