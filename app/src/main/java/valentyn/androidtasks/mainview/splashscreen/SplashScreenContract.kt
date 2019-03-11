package valentyn.androidtasks.mainview.splashscreen

import valentyn.androidtasks.BaseContract

interface SplashScreenContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun onAttach(view: View)

        fun initDatabase(progress :Int)

        fun setConfiguration(progress :Int)

        fun initData(progress :Int)

        fun finishProgress()

        fun showProgressBar()

    }

    interface View : BaseContract.View {

        fun showProgressBar()

        fun incrementProgressBar(i:Int)

        fun initDatabase()

        fun startMainActivity()

    }

}