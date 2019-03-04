package valentyn.androidtasks.mainview.splashscreen

import valentyn.androidtasks.data.source.local.RealmDatabase
import valentyn.androidtasks.data.source.repository.DataRepository

class SplashScreenPresenter : SplashScreenContract.Presenter {

    private var view: SplashScreenContract.View? = null

    override fun onAttach(view: SplashScreenContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun showProgressBar() {
        view?.showProgressBar()
    }

    override fun initDatabase() {
        view?.initDatabase()
        view?.incrementProgressBar(30)
    }

    override fun setConfiguration() {
        RealmDatabase.setRealmConfiguration()
        view?.incrementProgressBar(30)
    }

    override fun initDatas() {
        DataRepository.initAllData()
        view?.incrementProgressBar(40)
    }

    override fun finishProgress() {
        view?.startMainActivity()
    }

    override fun onDetach() {
        view = null
    }


}