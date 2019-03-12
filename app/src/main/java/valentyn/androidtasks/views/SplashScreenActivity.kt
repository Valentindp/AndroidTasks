package valentyn.androidtasks.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import valentyn.androidtasks.R
import android.os.Bundle
import android.os.Handler
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_splash_screen.*
import valentyn.androidtasks.repository.RealmRepository

class SplashScreenActivity : AppCompatActivity() {
    private var progressStatus = 0
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler(Handler.Callback {

            progressBarHorizontal.progress = progressStatus
            textViewProgressBar.text = "${progressStatus}/${progressBarHorizontal.max}"

            when (progressStatus) {
                0 -> {
                    Realm.init(this)
                    progressStatus += 30
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                30 -> {
                    Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
                    progressStatus += 30
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                60 -> {
                    RealmRepository.initDB()
                    progressStatus += 40
                    handler?.sendEmptyMessageDelayed(0, 100)
                }
                100 -> {
                    startActivity(
                        Intent(
                            this@SplashScreenActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }
            }

            true
        })

        handler?.sendEmptyMessage(0)
    }

}