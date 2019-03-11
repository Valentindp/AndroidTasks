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

    private val PROGRESS_START = 0
    private val PROGRESS_INIT_DATABASE_PART = 25
    private val PROGRESS_SET_CONFIG_PART = 35
    private val PROGRESS_INIT_DATA_PART= 40
    private val PROGRESS_SET_CONFIG_FINISH = 60
    private val PROGRESS_FINISH = 100

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
                PROGRESS_START -> {
                    presenter.initDatabase(PROGRESS_INIT_DATABASE_PART)
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                PROGRESS_INIT_DATABASE_PART -> {
                    presenter.setConfiguration(PROGRESS_SET_CONFIG_PART)
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                PROGRESS_SET_CONFIG_FINISH -> {
                    presenter.initData(PROGRESS_INIT_DATA_PART)
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                PROGRESS_FINISH -> {
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