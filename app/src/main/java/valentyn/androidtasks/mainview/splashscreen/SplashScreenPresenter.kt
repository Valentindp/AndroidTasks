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

    override fun initDatabase(progress :Int) {
        view?.initDatabase()
        view?.incrementProgressBar(progress)
    }

    override fun setConfiguration(progress :Int) {
        RealmDatabase.setRealmConfiguration()
        view?.incrementProgressBar(progress)
    }

    override fun initData(progress :Int) {
        DataRepository.initAllData()
        view?.incrementProgressBar(progress)
    }

    override fun finishProgress() {
        view?.startMainActivity()
    }

    override fun onDetach() {
        view = null
    }


}