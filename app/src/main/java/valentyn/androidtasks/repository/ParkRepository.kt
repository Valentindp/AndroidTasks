package valentyn.androidtasks.repository

import valentyn.androidtasks.models.Park
import java.util.*

object ParkRepository {

    val dataParks: List<Park> = listOf(
        Park(
            UUID.randomUUID().toString(),
            "Йеллоустоун",
            "https://s.zagranitsa.com/images/articles/2198/870x486/35fc63a20eed9e6598e62706d71ce22a.jpg?1448881791",
            "Самым первым национальным парком в США стал Йеллоустоун, основанный 1 марта 1872 года. ",
            "Монтана и Айдахо",
            "",
            false
        ),
        Park(
            UUID.randomUUID().toString(),
            "Гранд-Каньон",
            "https://s.zagranitsa.com/images/articles/2198/870x486/ba0a559b5a46a763d3b47c20a4347bef.jpg?1448881791",
            "Один из старейших и крупнейших парков в стране — Национальный парк Гранд-Каньон",
            "Аризона",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Гавайские вулканы",
            "https://s.zagranitsa.com/images/articles/2198/870x486/5c6c37165bebd8d604541ce095e52d39.jpg?1448881791",
            "Парк на острове Гавайи имеет площадь 1348 квадратных километров",
            "Гавайи",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Глейшер-Бей",
            "https://s.zagranitsa.com/images/articles/4671/426x270/f25ac938093e37caed295d48a257e0b5.jpg",
            "Национальный парк Глейшер-Бей находится на юго-восточном побережье Аляски, неподалеку от городка Джуно.",
            "Аляска",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Йосемити",
            "https://s.zagranitsa.com/images/articles/2198/870x486/bf7f75c2cc308d9b45b9603e71f521ab.jpg?1448881792",
            "Национальный парк Йосемити находится на западных склонах горного хребта Сьерра-Невада",
            "Калифорния",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Акадия",
            "https://s.zagranitsa.com/images/articles/2198/870x486/0229924009cd2fbd79b06033bff494a2.jpg?1448881792",
            "Национальные парки США в Новой Англии представлены в единственном экземпляре — парком Акадия",
            "Мэн",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Гранд-Титон",
            "https://s.zagranitsa.com/images/articles/2198/870x486/7eacd0b3b940eb6318020b3b86980b83.jpg?1448881792",
            "Крупнейший национальный парк США площадью около 130 000 га включает большую часть горного хребта Титон и северную часть долины Джексон-Хоул",
            "Вайоминг",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Арки",
            "https://s.zagranitsa.com/images/articles/2198/870x486/156b61cbb93608674fbd25276542c40d.jpg?1448881792",
            "Парк неподалеку от города Моаб занимает площадь в 309 квадратных километров",
            "Юта",
            "",
            false
        )
        ,
        Park(
            UUID.randomUUID().toString(),
            "Бэдлендс",
            "https://s.zagranitsa.com/images/articles/2198/870x486/557a6cd31e62cef3f23ff4cac80f4edf.jpg?1448881793",
            "Парк Бэдлендс занимает площадь в 982 квадратных километра",
            "Южная Дакота",
            "",
            false
        )
    )


}
