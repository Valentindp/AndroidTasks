package valentyn.androidtasks.presenters

import valentyn.androidtasks.models.Data

class DatasRepository{

     fun getDataset(): List<Data> {
      val Dataset: List<Data> = listOf(Data(
          1,
          "Сан-Франциско",
          "https://s.zagranitsa.com/images/articles/4671/426x270/c9ccd2c338f2b384e12e2452b008a708.jpg",
          "Description",
          "",
          "",
          false),
          Data(2,
              "Сиэтл",
              "https://s.zagranitsa.com/images/articles/4671/426x270/27de142f95eacfdbf18647859a2b45cf.jpg",
              "Description",
              "",
              "",
              false)
          ,
          Data(3,
              "Атланта",
              "https://s.zagranitsa.com/images/articles/4671/426x270/f6e9b81ceded8fd19bb8764f0cf4750a.jpg",
              "Description",
              "",
              "",
              false)
          ,
          Data(4,
              "Филадельфия",
              "https://s.zagranitsa.com/images/articles/4671/426x270/f25ac938093e37caed295d48a257e0b5.jpg",
              "Description",
              "",
              "",
              false)
          ,
          Data(5,
              "Миннеаполис",
              "https://s.zagranitsa.com/images/articles/4671/426x270/d4e1a2666699b0ecd9930cf0a0c4a5c8.jpg",
              "Description",
              "",
              "",
              false)
          ,
          Data(6,
              "Нашвилл",
              "https://s.zagranitsa.com/images/articles/4671/426x270/1d79717af24d2e999df058c98ea0e1ee.jpg",
              "Description",
              "",
              "",
              false)
          ,
          Data(7,
              "Чикаго",
              "https://s.zagranitsa.com/images/articles/4671/426x270/e241ea77af9731119128776afd548859.jpg",
              "Description",
              "",
              "",
              false))
        return Dataset
    }
}