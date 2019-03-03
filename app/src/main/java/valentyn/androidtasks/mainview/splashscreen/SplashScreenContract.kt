package valentyn.androidtasks.mainview.splashscreen

import valentyn.androidtasks.BaseContract

interface SplashScreenContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View)

        fun finishProgress()

        fun initDatabase()

        fun setConfiguration()

        fun initDatas()

        fun showProgressBar()

    }

    interface View : BaseContract.View {

        fun showProgressBar()

        fun incrementProgressBar(i:Int)

        fun initDatabase()

        fun startMainActivity()

    }

}