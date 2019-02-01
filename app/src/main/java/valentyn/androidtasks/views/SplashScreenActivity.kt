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

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())
        RealmRepository.initDB()

        handler = Handler(Handler.Callback {
            progressStatus += 10
            progressBarHorizontal.progress = progressStatus
            textViewProgressBar.text = "${progressStatus}/${progressBarHorizontal.max}"

            if (progressStatus == 100) {
                startActivity(
                    Intent(
                        this@SplashScreenActivity,
                        MainActivity::class.java
                    )
                )
                finish()

            } else handler?.sendEmptyMessageDelayed(0, 100)

            true
        })

        handler?.sendEmptyMessage(0)
    }

}