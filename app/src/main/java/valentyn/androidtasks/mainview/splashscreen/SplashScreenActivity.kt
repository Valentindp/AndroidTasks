package valentyn.androidtasks.mainview.splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.R
import android.os.Bundle
import android.os.Handler
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_splash_screen.*
import valentyn.androidtasks.mainview.MainActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenContract.View {

    private var progressStatus = 0
    private var handler: Handler? = null
    private val presenter: SplashScreenPresenter = SplashScreenPresenter()

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler(Handler.Callback {

            presenter.showProgressBar()

            when (progressStatus) {
                0 -> {
                    presenter.initDatabase()
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                30 -> {
                    presenter.setConfiguration()
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                60 -> {
                    presenter.initDatas()
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                100 -> {
                    presenter.finishProgress()
                }
            }

            true
        })

        handler?.sendEmptyMessage(0)
    }

    override fun showProgressBar() {
        progressBarHorizontal.progress = progressStatus
        textViewProgressBar.text = "${progressStatus}/${progressBarHorizontal.max}"
    }

    override fun incrementProgressBar(i: Int) {
        progressStatus += i
    }

    override fun startMainActivity() {
        startActivity(
            Intent(
                this@SplashScreenActivity,
                MainActivity::class.java
            )
        )
        finish()
    }

    override fun initDatabase() {
        Realm.init(this)
    }

    override fun onStop() {
        presenter.onDetach()
        super.onStop()
    }

}